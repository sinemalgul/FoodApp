package com.sinemalgul.foodapp.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sinemalgul.foodapp.data.model.Meal
import com.sinemalgul.foodapp.data.model.Restaurant
import com.sinemalgul.foodapp.source.local.CartDAO
import com.sinemalgul.foodapp.source.local.CartRoomDatabase
import com.sinemalgul.foodapp.source.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealRepository(context: Context) {
    val restaurantList = MutableLiveData<List<Restaurant>>()

    val mealList = MutableLiveData<List<Meal>>()

    val cartMealList = MutableLiveData<List<Meal>>()

    var isMealAddedCart = MutableLiveData<Pair<Boolean, String>>()

    private val apiService = RetrofitClient.apiService

    private val cartDAO: CartDAO? =
        CartRoomDatabase.productsCartRoomDatabase(context)?.productsCartDAOInterface()

    fun getRestaurants() {
        apiService.getMeals().enqueue(object : Callback<List<Meal>> {

            override fun onResponse(call: Call<List<Meal>>, response: Response<List<Meal>>) {
                if (response.body().isNullOrEmpty()) restaurantList.value = emptyList()
                else {

                    val restaurants = mutableListOf<Restaurant>()

                    response.body()?.forEach {
                        restaurants.add(Restaurant(it.category, it.imageTwo))
                    }

                    restaurantList.value = restaurants.toSet().toMutableList()
                }
            }

            override fun onFailure(call: Call<List<Meal>>, t: Throwable) {
                Log.d("Failure", t.message.orEmpty())
            }
        })
    }

    fun getMealsByRestaurant(category: String) {
        apiService.getMealsByRestaurant(category = category)
            .enqueue(object : Callback<List<Meal>> {

                override fun onResponse(
                    call: Call<List<Meal>>,
                    response: Response<List<Meal>>,
                ) {
                    if (response.body().isNullOrEmpty()) mealList.value = emptyList()
                    else mealList.value = response.body()
                }

                override fun onFailure(call: Call<List<Meal>>, t: Throwable) {
                    Log.d("Failure", t.message.orEmpty())
                }
            })
    }


    fun searchRestaurants(query: String) {
        apiService.searchRestaurants(query).enqueue(object : Callback<List<Meal>> {
            override fun onResponse(call: Call<List<Meal>>, response: Response<List<Meal>>) {
                if (response.body().isNullOrEmpty()) mealList.value = emptyList()
                else {
                    val restaurants = mutableListOf<Restaurant>()

                    response.body()?.forEach {
                        restaurants.add(Restaurant(it.category, it.imageTwo))
                    }

                    restaurantList.value = restaurants.toSet().toMutableList()
                }
            }

            override fun onFailure(call: Call<List<Meal>>, t: Throwable) {
                Log.d("Failure", t.message.orEmpty())
            }
        })
    }

    fun addMealToCart(meal: Meal) {
        cartDAO?.getMealsTitlesCart()?.let {
            isMealAddedCart.value = if (it.contains(meal.title).not()) {
                cartDAO.addMealToCart(meal)
                Pair(true, meal.title)
            } else Pair(false, meal.title)
        }
    }

    fun deleteMealFromCart(id: Int) {
        cartDAO?.deleteFromCart(id)
    }

    fun getCartMeals() {
        cartDAO?.getCartMeals()?.let {
            cartMealList.value = it
        }
    }

    fun clearCart() = cartDAO?.clearCart()
}