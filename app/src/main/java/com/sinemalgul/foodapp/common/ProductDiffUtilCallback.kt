package com.sinemalgul.foodapp.common

import androidx.recyclerview.widget.DiffUtil
import com.sinemalgul.foodapp.data.model.Meal

object ProductDiffUtilCallback : DiffUtil.ItemCallback<Meal>() {
    override fun areItemsTheSame(oldItem: Meal, newItem: Meal) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Meal, newItem: Meal) = oldItem == newItem
}