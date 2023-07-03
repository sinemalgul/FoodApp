package com.sinemalgul.foodapp.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sinemalgul.foodapp.data.model.Restaurant
import com.sinemalgul.foodapp.data.repository.MealRepository

class HomeViewModel(context: Context) : ViewModel() {

    private val restaurantRepository = MealRepository(context)

    private var _restaurantList = MutableLiveData<List<Restaurant>>()
    val restaurantList: LiveData<List<Restaurant>>
        get() = _restaurantList

    init {
        restaurants()
    }

    fun restaurants() {
        restaurantRepository.getRestaurants()
        _restaurantList = restaurantRepository.restaurantList
    }

    fun searchRestaurant(query: String) {
        restaurantRepository.searchRestaurants(query)
        _restaurantList = restaurantRepository.restaurantList
    }
}