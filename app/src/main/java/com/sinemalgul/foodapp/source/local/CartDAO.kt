package com.sinemalgul.foodapp.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sinemalgul.foodapp.data.model.Meal

@Dao
interface CartDAO {

    @Insert
    fun addMealToCart(mealCart: Meal)

    @Query("SELECT * FROM cart")
    fun getCartMeals(): List<Meal>?

    @Query("SELECT title FROM cart")
    fun getMealsTitlesCart(): List<String>?

    @Query("DELETE FROM cart WHERE id = :idInput")
    fun deleteFromCart(idInput: Int)

    @Query("DELETE FROM cart")
    fun clearCart()
}