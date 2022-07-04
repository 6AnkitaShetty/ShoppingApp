package com.example.shoppingapp.features.home.domain.usecase

import com.example.shoppingapp.features.home.data.local.model.CartItem
import com.example.shoppingapp.features.home.domain.repository.CartRepository

class GetCartItemsUseCase(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(
    ): List<CartItem> {
        return cartRepository.getCartItems()
    }
}