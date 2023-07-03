package com.sinemalgul.foodapp.ui.onboarding

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sinemalgul.foodapp.R

class ViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    private val imageList = listOf(R.drawable.first, R.drawable.second, R.drawable.third)
    private val titleList = listOf("Select Restaurant", "Order Food", "Fast Delivery")
    private val descList =
        listOf("Order from the best local restaurants with easy, on-demand delivery.",
            "Easily find the type of food you're craving.",
            "To get your food, our delivery executives deliver at your doorstep.")

    override fun getItemCount() = 3

    override fun createFragment(position: Int) =
        OnBoardingChildFragment.newInstance(
            imageList[position],
            titleList[position],
            descList[position]
        )
}