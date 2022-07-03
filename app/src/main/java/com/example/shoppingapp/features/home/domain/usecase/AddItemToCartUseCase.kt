package com.example.shoppingapp.features.home.domain.usecase

import com.example.shoppingapp.features.home.data.local.model.CartItem
import com.example.shoppingapp.features.home.data.remote.model.Product
import com.example.shoppingapp.features.home.domain.repository.CartRepository

class AddItemToCartUseCase (
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(product: Product): Long? {
        val cartItemWithId = cartRepository.getCartItemById(product.productId)
        val count: Int = if (cartItemWithId == null) {
            1
        } else {
            cartItemWithId.count?.plus(1) ?: 1
        }
        val cartItem = CartItem(
            product.productId,
            product.productName,
            product.productImage,
            count,
            product.salesPriceIncVat
        )

        return cartRepository.insertItemToCart(cartItem)
    }
}