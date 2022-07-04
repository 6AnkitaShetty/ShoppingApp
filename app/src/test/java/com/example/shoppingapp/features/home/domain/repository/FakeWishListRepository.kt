package com.example.shoppingapp.features.home.domain.repository

import com.example.shoppingapp.features.home.data.FakeDataUtil
import com.example.shoppingapp.features.home.data.local.model.WishListItem

class FakeWishListRepository : WishListRepository {
    private val wishListItems = mutableListOf<WishListItem>()
    override suspend fun getWishListItems(): List<WishListItem> {
        wishListItems.add(FakeDataUtil.getWishListItem())
        return wishListItems
    }

    override suspend fun getWishListById(id: Int): WishListItem? {
       return wishListItems.firstOrNull { it.productId == id }
    }

    override suspend fun insertItemToWishList(wishListItem: WishListItem): Long? {
        wishListItems.add(wishListItem)
        return 1
    }

    override suspend fun getWishListItemsCount(): Int? {
        return wishListItems.size
    }

    override suspend fun getTotalWishListPrice(): Double? {
        return wishListItems.sumOf { it.price ?: 0.0 }
    }

    override suspend fun delete(wishListItem: WishListItem) {
        wishListItems.remove(wishListItem)
    }

    override suspend fun deleteAll() {
        wishListItems.clear()
    }
}