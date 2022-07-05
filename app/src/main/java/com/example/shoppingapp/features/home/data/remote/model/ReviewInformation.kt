package com.example.shoppingapp.features.home.data.remote.model

import java.io.Serializable

data class ReviewInformation(
    val reviewSummary: ReviewSummary,
    val reviews: List<Any>
) : Serializable