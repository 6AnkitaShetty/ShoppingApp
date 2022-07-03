package com.example.shoppingapp.features.home.domain.usecase

import com.example.shoppingapp.features.home.domain.repository.WishListRepository

class GetWishListItemCountUseCase(
    private val wishListRepository: WishListRepository
) {

    suspend operator fun invoke(): Int? {
        return wishListRepository.getWishListItemsCount()
    }
}