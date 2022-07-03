package com.example.shoppingapp.features.home.domain.usecase

import com.example.shoppingapp.features.home.data.local.model.CartItem
import com.example.shoppingapp.features.home.domain.repository.CartRepository

class GetCartItemByIdUseCase (
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke(id:Int) : CartItem? {
        return cartRepository.getCartItemById(id)
    }
}