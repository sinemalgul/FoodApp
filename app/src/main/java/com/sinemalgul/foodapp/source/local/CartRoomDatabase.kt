package com.sinemalgul.foodapp.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sinemalgul.foodapp.data.model.Meal

@Database(entities = [Meal::class], version = 1)
abstract class CartRoomDatabase : RoomDatabase() {

    abstract fun productsCartDAOInterface(): CartDAO

    companion object {

        private var instance: CartRoomDatabase? = null

        fun productsCartRoomDatabase(context: Context): CartRoomDatabase? {

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    CartRoomDatabase::class.java,
                    "meal_cart.db"
                ).allowMainThreadQueries().build()
            }
            return instance
        }
    }
}