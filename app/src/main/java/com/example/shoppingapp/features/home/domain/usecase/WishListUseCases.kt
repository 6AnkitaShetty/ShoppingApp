package com.example.shoppingapp.features.home.domain.usecase

data class WishListUseCases(
    val getWishListItemsUseCase: GetWishListItemsUseCase,
    val getWishListItemCountUseCase: GetWishListItemCountUseCase,
    val addItemToWishListUseCase: AddItemToWishListUseCase,
    val getWishListItemByIdUseCase: GetWishListItemByIdUseCase,
    val deleteWishListItemUseCase: DeleteWishListItemUseCase
)