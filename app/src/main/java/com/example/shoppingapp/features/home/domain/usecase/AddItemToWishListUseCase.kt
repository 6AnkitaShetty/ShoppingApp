package com.example.shoppingapp.features.home.domain.usecase

import com.example.shoppingapp.features.home.data.local.model.WishListItem
import com.example.shoppingapp.features.home.data.remote.model.Product
import com.example.shoppingapp.features.home.domain.repository.WishListRepository

class AddItemToWishListUseCase(
    private val wishListRepository: WishListRepository
) {
    suspend operator fun invoke(product: Product): Long? {
        val wishListItem = WishListItem(
            product.productId,
            product.productName,
            product.productImage,
            product.salesPriceIncVat
        )

        val itemInWishListByID = wishListRepository.getWishListById(product.productId)
        return if (itemInWishListByID != null) {
            wishListRepository.delete(wishListItem)
            0
        } else {
            wishListRepository.insertItemToWishList(wishListItem)
        }
    }
}