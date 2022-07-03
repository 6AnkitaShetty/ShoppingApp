package com.example.shoppingapp.features.home.domain.repository

import com.example.shoppingapp.features.home.data.local.model.WishListItem

interface WishListRepository {
    suspend fun getWishListItems(): List<WishListItem>

    suspend fun getWishListById(id: Int): WishListItem?

    suspend fun insertItemToWishList(wishListItem: WishListItem): Long?

    suspend fun getWishListItemsCount(): Int?

    suspend fun getTotalWishListPrice(): Double?

    suspend fun delete(wishListItem: WishListItem)

    suspend fun deleteAll()
}