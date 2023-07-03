package com.sinemalgul.foodapp.ui.meal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sinemalgul.foodapp.data.model.Meal
import com.sinemalgul.foodapp.databinding.ItemMealsBinding

class MealAdapter : ListAdapter<Meal, MealAdapter.MealViewHolder>(MealDiffUtilCallback) {

    var onCartClick: (Meal) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val binding = ItemMealsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(binding)
    }

    inner class MealViewHolder(private val binding: ItemMealsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(meal: Meal) {

            with(binding) {
                mealTitleTextView.text = meal.title
                mealPriceTextView.text = "$ ${meal.price}"
                mealDescriptionTextView.text = meal.description
                Glide.with(mealImageView).load(meal.image).into(mealImageView)

                ivCart.setOnClickListener { onCartClick(meal) }
            }
        }
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) =
        holder.bind(currentList[position])
}

object MealDiffUtilCallback : DiffUtil.ItemCallback<Meal>() {
    override fun areItemsTheSame(oldItem: Meal, newItem: Meal) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Meal, newItem: Meal) = oldItem == newItem
}