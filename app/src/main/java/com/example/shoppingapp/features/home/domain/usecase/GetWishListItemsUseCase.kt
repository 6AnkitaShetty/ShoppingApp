package com.example.shoppingapp.features.home.domain.usecase

import com.example.shoppingapp.features.home.data.local.model.WishListItem
import com.example.shoppingapp.features.home.domain.repository.WishListRepository

class GetWishListItemsUseCase (
    private val wishListRepository: WishListRepository
) {
    suspend operator fun invoke(
    ): List<WishListItem> {
        return wishListRepository.getWishListItems()
    }
}