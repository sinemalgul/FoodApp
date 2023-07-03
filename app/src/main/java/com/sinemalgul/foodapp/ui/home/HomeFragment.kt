package com.sinemalgul.foodapp.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.sinemalgul.foodapp.R
import com.sinemalgul.foodapp.data.model.Restaurant
import com.sinemalgul.foodapp.databinding.FragmentHomeBinding
import kotlin.math.abs

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val restaurantsAdapter by lazy { RestaurantsAdapter() }

    private val homeViewModel by lazy { HomeViewModel(requireContext()) }

    private lateinit var handler: Handler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initViews()

        restaurantsAdapter.onRestaurantClick = {
            val action = HomeFragmentDirections.homeToMeal(it)
            findNavController().navigate(action)
        }
    }

    private fun initViews() = with(binding) {
        vpCampaigns.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 3000)
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    homeViewModel.restaurants()
                    cvCampaigns.visibility = View.VISIBLE
                } else {
                    homeViewModel.searchRestaurant(newText)
                    cvCampaigns.visibility = View.GONE
                }
                return false
            }
        })
    }


    private fun initObservers() {
        homeViewModel.restaurantList.observe(viewLifecycleOwner) { restaurantList ->
            if (restaurantList != null) {
                restaurantsAdapter.submitList(restaurantList)
                binding.rvRestaurants.adapter = restaurantsAdapter

                initSlider { image ->
                    when (image) {
                        R.drawable.dominoskampanya -> {
                            restaurantList.find { it.name == "Domino's Pizza" }?.let { restaurant ->
                                val action = HomeFragmentDirections.homeToMeal(restaurant)
                                findNavController().navigate(action)
                            }
                        }
                        R.drawable.burgerkingkampanya -> {
                            restaurantList.find { it.name == "Burger King" }?.let { restaurant ->
                                val action = HomeFragmentDirections.homeToMeal(restaurant)
                                findNavController().navigate(action)
                            }
                        }
                        R.drawable.pizzahutkampanya -> {
                            restaurantList.find { it.name == "Pizza Hut" }?.let { restaurant ->
                                val action = HomeFragmentDirections.homeToMeal(restaurant)
                                findNavController().navigate(action)
                            }
                        }
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Empty List", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val runnable = Runnable {
        binding.vpCampaigns.currentItem = binding.vpCampaigns.currentItem + 1
    }

    private fun initSlider(onCampaignClick: (Int) -> Unit) = with(binding) {

        handler = Handler(Looper.getMainLooper())

        CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
            addTransformer { page, position ->
                val r = 1 - abs(position)
                page.scaleY = 0.85f + r * 0.14f
            }
            binding.vpCampaigns.setPageTransformer(this)
        }

        val campaignList =
            arrayListOf(
                R.drawable.dominoskampanya,
                R.drawable.burgerkingkampanya,
                R.drawable.pizzahutkampanya
            )

        with(vpCampaigns) {
            adapter = CampaignAdapter(campaignList, binding.vpCampaigns).apply {
                this.onCampaignClick = {
                    onCampaignClick(it)
                }
            }
            offscreenPageLimit = 3
            clipToPadding = true
            clipChildren = false
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
    }
}