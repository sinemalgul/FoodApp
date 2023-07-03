package com.sinemalgul.foodapp.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sinemalgul.foodapp.common.ProductDiffUtilCallback
import com.sinemalgul.foodapp.data.model.Meal
import com.sinemalgul.foodapp.databinding.ItemCartMealsBinding
import kotlinx.coroutines.delay

class CartMealsAdapter :
    ListAdapter<Meal, CartMealsAdapter.CartViewHolder>(ProductDiffUtilCallback) {

    var onDeleteClick: (Int) -> Unit = {}
    var onIncreaseClick: (Double) -> Unit = {}
    var onDecreaseClick: (Double) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding =
            ItemCartMealsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    inner class CartViewHolder(private var binding: ItemCartMealsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(meal: Meal) {

            var productCount = 1

            with(binding) {

                tvMealName.text = meal.title
                tvPrice.text = "${meal.price}$"
                Glide.with(imgMeal).load(meal.image).into(imgMeal)
                tvProductCount.text = productCount.toString()

                imgIncrease.setOnClickListener {
                    onIncreaseClick(meal.price)
                    productCount++
                    tvProductCount.text = productCount.toString()
                }

                imgDecrease.setOnClickListener {
                    if (productCount != 1) {
                        onDecreaseClick(meal.price)
                        productCount--
                        tvProductCount.text = productCount.toString()
                    } else {
                        onDeleteClick(meal.id ?: 1)
                    }
                }

                imgDelete.setOnClickListener { onDeleteClick(meal.id ?: 1) }
            }
        }
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) =
        holder.bind(currentList[position])

    override fun getItemCount() = currentList.size
}