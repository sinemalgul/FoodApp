package com.sinemalgul.foodapp.ui.cart

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sinemalgul.foodapp.data.model.Meal
import com.sinemalgul.foodapp.data.repository.MealRepository

class CartViewModel(context: Context) : ViewModel() {

    private val mealRepository = MealRepository(context)

    private var _cartMealList = MutableLiveData<List<Meal>>()
    val cartMealList: LiveData<List<Meal>>
        get() = _cartMealList

    private val _totalAmount = MutableLiveData(0.0)
    val totalAmount: LiveData<Double> = _totalAmount

    init {
        cartMeals()
    }

    private fun cartMeals() {
        mealRepository.getCartMeals()
        _cartMealList = mealRepository.cartMealList
    }


    fun deleteFromCart(id: Int) {
        mealRepository.deleteMealFromCart(id)
        resetTotalAmount()
        cartMeals()
    }

    fun increase(price: Double) {
        _totalAmount.value = _totalAmount.value?.plus(price)
    }

    fun decrease(price: Double) {
        _totalAmount.value = _totalAmount.value?.minus(price)
    }

    private fun resetTotalAmount() {
        _totalAmount.value = 0.0
    }
}

