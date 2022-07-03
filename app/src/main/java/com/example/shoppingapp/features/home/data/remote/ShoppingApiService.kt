package com.example.shoppingapp.features.home.data.remote

import com.example.shoppingapp.features.home.data.remote.model.ApiResponse
import com.example.shoppingapp.util.Constants.PAGE
import com.example.shoppingapp.util.Constants.QUERY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ShoppingApiService {

    @GET("search")
    suspend fun fetchProducts(
        @Query("query") query: String = QUERY,
        @Query("page") page: Int = PAGE
    ): Response<ApiResponse>
}