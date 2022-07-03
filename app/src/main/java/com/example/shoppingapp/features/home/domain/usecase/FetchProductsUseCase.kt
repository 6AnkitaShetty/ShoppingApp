package com.example.shoppingapp.features.home.domain.usecase

import com.example.shoppingapp.features.home.data.remote.model.Product
import com.example.shoppingapp.features.home.domain.repository.ProductsRepository
import com.example.shoppingapp.util.Resource

class FetchProductsUseCase(
    private val productsRepository: ProductsRepository,
    private val wishListUseCases: WishListUseCases
) {
    suspend operator fun invoke(): Resource<List<Product>> {
        val products = productsRepository.getProducts()
        products.data?.map {
            val product = wishListUseCases.getWishListItemByIdUseCase.invoke(it.productId)
            it.isWishListed = product != null
        }

        return products
    }
}