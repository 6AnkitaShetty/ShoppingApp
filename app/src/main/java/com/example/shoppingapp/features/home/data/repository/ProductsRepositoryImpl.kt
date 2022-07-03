package com.example.shoppingapp.features.home.data.repository

import com.example.shoppingapp.R
import com.example.shoppingapp.features.home.data.remote.model.ApiResponse
import com.example.shoppingapp.features.home.data.remote.model.Product
import com.example.shoppingapp.features.home.data.remote.ShoppingApiService
import com.example.shoppingapp.features.home.domain.repository.ProductsRepository
import com.example.shoppingapp.util.Resource
import retrofit2.Response

class ProductsRepositoryImpl(
    private val shoppingApiService: ShoppingApiService
) : ProductsRepository {
    override suspend fun getProducts(): Resource<List<Product>> {
        return responseToResource(shoppingApiService.fetchProducts())

    }

    private fun responseToResource(
        response: Response<ApiResponse>,
    ): Resource<List<Product>> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result.products)
            }
        }
        return Resource.Error(R.string.error_message)
    }
}