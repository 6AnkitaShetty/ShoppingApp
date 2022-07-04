package com.example.shoppingapp.features.home.domain.usecase

import com.example.shoppingapp.features.home.domain.repository.CartRepository

class GetTotalCartPriceUseCase(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(): Double? {
        return cartRepository.getTotalCartPrice()
    }
}