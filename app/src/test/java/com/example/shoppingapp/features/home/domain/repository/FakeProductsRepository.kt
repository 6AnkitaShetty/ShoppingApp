package com.example.shoppingapp.features.home.domain.repository

import com.example.shoppingapp.features.home.data.FakeDataUtil
import com.example.shoppingapp.features.home.data.remote.model.Product
import com.example.shoppingapp.util.Resource

class FakeProductsRepository : ProductsRepository {
    override suspend fun getProducts(): Resource<List<Product>> {
        return Resource.Success(FakeDataUtil.getFakeProducts())
    }
}