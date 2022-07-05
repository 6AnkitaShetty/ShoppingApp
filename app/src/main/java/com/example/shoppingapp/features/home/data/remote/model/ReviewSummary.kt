package com.example.shoppingapp.features.home.data.remote.model

import java.io.Serializable

data class ReviewSummary(
    val reviewAverage: Double,
    val reviewCount: Int
) : Serializable