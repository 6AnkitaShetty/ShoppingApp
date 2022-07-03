package com.example.shoppingapp.features.home.domain.usecase

import com.example.shoppingapp.features.home.data.local.model.CartItem
import com.example.shoppingapp.features.home.data.local.model.WishListItem
import com.example.shoppingapp.features.home.domain.repository.CartRepository

class AddWishListItemToCartUseCase(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(wishListItem: WishListItem): Long? {

        val cartItemWithId = cartRepository.getCartItemById(wishListItem.productId)
        val count: Int = if (cartItemWithId == null) {
            1
        } else {
            cartItemWithId.count?.plus(1) ?: 1
        }
        val cartItem = CartItem(
            wishListItem.productId,
            wishListItem.productName,
            wishListItem.productImage,
            count,
            wishListItem.price?.times(count)
        )
        return cartRepository.insertItemToCart(cartItem)
    }
}