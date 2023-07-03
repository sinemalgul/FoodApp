package com.sinemalgul.foodapp.ui.meal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.sinemalgul.foodapp.R
import com.sinemalgul.foodapp.databinding.FragmentMealBinding

class MealFragment : Fragment() {

    private lateinit var binding: FragmentMealBinding

    private val mealAdapter by lazy { MealAdapter() }

    private val mealViewModel by lazy { MealViewModel(requireContext()) }

    private val args: MealFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMealBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initObservers()

        mealAdapter.onCartClick = {
            mealViewModel.addMealToCart(it)
        }
    }

    private fun initViews() = with(binding) {
        args.restaurant.let {
            mealViewModel.getMealsByRestaurant(it.name)
            restaurantName.text = it.name
            Glide.with(imgRestaurants).load(it.image).into(imgRestaurants)
        }
    }

    private fun initObservers() {
        mealViewModel.mealList.observe(viewLifecycleOwner) {
            if (it != null) {
                mealAdapter.submitList(it)
                binding.mealRecyclerView.adapter = mealAdapter
            } else {
                Toast.makeText(requireContext(), "Empty Meal List", Toast.LENGTH_SHORT).show()
            }
        }

        mealViewModel.isMealAddedCart.observe(viewLifecycleOwner) {
            if (it.first) {
                Snackbar.make(requireView(), "Meal added to cart!", 1000).show()
            } else {
                findNavController().navigate(R.id.mealToCart)
                Snackbar.make(requireView(), "Already in the cart!", 1000).show()
            }
        }
    }
}