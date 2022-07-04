package com.example.shoppingapp.features.home.domain.usecase

import com.example.shoppingapp.features.home.data.local.model.WishListItem
import com.example.shoppingapp.features.home.domain.repository.WishListRepository

class GetWishListItemByIdUseCase(
    private val wishListRepository: WishListRepository
) {
    suspend operator fun invoke(id: Int): WishListItem? {
        return wishListRepository.getWishListById(id)
    }
}