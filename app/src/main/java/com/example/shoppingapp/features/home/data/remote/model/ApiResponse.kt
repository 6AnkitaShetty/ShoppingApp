package com.example.shoppingapp.features.home.data.remote.model

data class ApiResponse(
    val currentPage: Int,
    val pageCount: Int,
    val pageSize: Int,
    val products: List<Product>,
    val totalResults: Int
)