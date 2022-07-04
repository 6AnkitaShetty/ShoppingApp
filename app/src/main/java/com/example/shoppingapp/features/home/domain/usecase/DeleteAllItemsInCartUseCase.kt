package com.example.shoppingapp.features.home.domain.usecase

import com.example.shoppingapp.features.home.domain.repository.CartRepository

class DeleteAllItemsInCartUseCase(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke() {
        cartRepository.deleteAll()
    }
}