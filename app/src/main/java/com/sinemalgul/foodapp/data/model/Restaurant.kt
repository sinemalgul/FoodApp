package com.sinemalgul.foodapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Restaurant(
    val name: String,
    val image: String
) : Parcelable