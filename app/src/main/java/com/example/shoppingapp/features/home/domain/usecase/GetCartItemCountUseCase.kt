package com.example.shoppingapp.features.home.domain.usecase

import com.example.shoppingapp.features.home.domain.repository.CartRepository

class GetCartItemCountUseCase(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(): Int? {
        return cartRepository.getCartItemsCount()
    }
}