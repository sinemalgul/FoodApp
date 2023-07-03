package com.sinemalgul.foodapp.ui.paymentsuccess

import android.content.Context
import androidx.lifecycle.ViewModel
import com.sinemalgul.foodapp.data.repository.MealRepository

class PaymentSuccessViewModel(context: Context) : ViewModel() {

    private val mealRepository = MealRepository(context)

    fun clearBag() {
        mealRepository.clearCart()
    }
}