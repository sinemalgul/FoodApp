package com.sinemalgul.foodapp.source.remote

import com.sinemalgul.foodapp.data.model.Meal
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface APIService {

    @POST("get_products_by_user.php")
    @FormUrlEncoded
    fun getMeals(
        @Field("user") user: String = "sinemalgul",
    ): Call<List<Meal>>

    @POST("get_products_by_user_and_category.php")
    @FormUrlEncoded
    fun getMealsByRestaurant(
        @Field("user") user: String = "sinemalgul",
        @Field("category") category: String,
    ): Call<List<Meal>>

    @POST("search_product_by_user.php")
    @FormUrlEncoded
    fun searchRestaurants(
        @Field("query") query: String,
        @Field("user") user: String = "sinemalgul",
    ): Call<List<Meal>>

    @POST("get_categories_by_user.php")
    @FormUrlEncoded
    fun getRestaurants(
        @Field("user") user: String = "sinemalgul",
    ): Call<List<String>>
}