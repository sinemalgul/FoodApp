package com.sinemalgul.foodapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sinemalgul.foodapp.data.model.Restaurant
import com.sinemalgul.foodapp.databinding.ItemRestaurantsBinding


class RestaurantsAdapter :
    ListAdapter<Restaurant, RestaurantsAdapter.RestaurantViewHolder>(RestaurantDiffUtilCallback) {

    var onRestaurantClick: (Restaurant) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding =
            ItemRestaurantsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) =
        holder.bind(currentList[position])

    inner class RestaurantViewHolder(private val binding: ItemRestaurantsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: Restaurant) {
            with(binding) {
                tvTitle.text = restaurant.name
                Glide.with(imgRestaurants).load(restaurant.image).into(imgRestaurants)

                root.setOnClickListener { onRestaurantClick(restaurant) }
            }
        }
    }
}

object RestaurantDiffUtilCallback : DiffUtil.ItemCallback<Restaurant>() {
    override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant) =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant) = oldItem == newItem
}