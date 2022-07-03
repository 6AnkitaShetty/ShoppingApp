package com.example.shoppingapp.features.home.domain.usecase

import com.example.shoppingapp.features.home.data.local.model.WishListItem
import com.example.shoppingapp.features.home.domain.repository.WishListRepository

class DeleteWishListItemUseCase(
    private val wishListRepository: WishListRepository
) {
    suspend operator fun invoke(wishListItem: WishListItem) {
        wishListRepository.delete(wishListItem)
    }
}