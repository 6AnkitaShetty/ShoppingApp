package com.example.shoppingapp.features.home.domain.usecase

data class CartUseCases(
    val getCartItemsUseCase: GetCartItemsUseCase,
    val getCartItemByIdUseCase: GetCartItemByIdUseCase,
    val addItemToCartUseCase: AddItemToCartUseCase,
    val deleteCartItemUseCase: DeleteCartItemUseCase,
    val cartItemCountUseCase: GetCartItemCountUseCase,
    val cartPriceUseCase: GetTotalCartPriceUseCase,
    val addWishListItemToCartUseCase: AddWishListItemToCartUseCase,
    val deleteAllItemsInCartUseCase: DeleteAllItemsInCartUseCase
)
