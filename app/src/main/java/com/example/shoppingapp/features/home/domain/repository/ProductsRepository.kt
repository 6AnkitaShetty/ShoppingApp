package com.example.shoppingapp.features.home.domain.repository

import com.example.shoppingapp.features.home.data.remote.model.Product
import com.example.shoppingapp.util.Resource

interface ProductsRepository {
    suspend fun getProducts(): Resource<List<Product>>

}