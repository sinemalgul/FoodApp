package com.sinemalgul.foodapp.ui.meal

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sinemalgul.foodapp.data.model.Meal
import com.sinemalgul.foodapp.data.repository.MealRepository

class MealViewModel(context: Context) : ViewModel() {

    private val mealRepository = MealRepository(context)

    private var _mealList = MutableLiveData<List<Meal>>()
    val mealList: LiveData<List<Meal>>
        get() = _mealList

    private var _isMealAddedCart = MutableLiveData<Pair<Boolean, String>>()
    val isMealAddedCart: LiveData<Pair<Boolean, String>>
        get() = _isMealAddedCart

    init {
        _mealList = mealRepository.mealList
        _isMealAddedCart = mealRepository.isMealAddedCart
    }

    fun getMealsByRestaurant(restaurantName: String) =
        mealRepository.getMealsByRestaurant(restaurantName)

    fun addMealToCart(meal: Meal) = mealRepository.addMealToCart(meal)
}