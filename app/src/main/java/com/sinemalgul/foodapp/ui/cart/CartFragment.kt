package com.sinemalgul.foodapp.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sinemalgul.foodapp.R
import com.sinemalgul.foodapp.databinding.FragmentCartBinding

class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding

    private val cartMealsAdapter by lazy { CartMealsAdapter() }

    private val cartViewModel by lazy { CartViewModel(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

        cartMealsAdapter.onDeleteClick = {
            cartViewModel.deleteFromCart(it)
        }

        cartMealsAdapter.onIncreaseClick = {
            cartViewModel.increase(it)
        }

        cartMealsAdapter.onDecreaseClick = {
            cartViewModel.decrease(it)
        }
    }

    private fun initObservers() {
        cartViewModel.cartMealList.observe(viewLifecycleOwner) {
            cartMealsAdapter.submitList(it)
            binding.rvCartMeals.adapter = cartMealsAdapter
            it.forEach { meal ->
                cartViewModel.increase(meal.price)
            }

            if (it.isNotEmpty()) {
                binding.btnBuyNow.isEnabled = true
                binding.btnBuyNow.setOnClickListener {
                    findNavController().navigate(R.id.cartToPayment)
                }
            }
        }

        cartViewModel.totalAmount.observe(viewLifecycleOwner) {
            binding.tvTotalAmount.text = String.format("%.2f$", it)
        }
    }
}