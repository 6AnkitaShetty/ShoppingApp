package com.example.shoppingapp.features.home.data.repository

import com.example.shoppingapp.features.home.data.local.WishListDao
import com.example.shoppingapp.features.home.data.local.model.WishListItem
import com.example.shoppingapp.features.home.domain.repository.WishListRepository

class WishListRepositoryImpl(
    private val wishListDao: WishListDao
) : WishListRepository {
    override suspend fun getWishListItems(): List<WishListItem> {
        return wishListDao.getWishListItems()
    }

    override suspend fun getWishListById(id: Int): WishListItem? {
        return wishListDao.getWishListItemById(id)
    }

    override suspend fun insertItemToWishList(wishListItem: WishListItem): Long? {
        return wishListDao.insertItemToWishList(wishListItem)
    }

    override suspend fun getWishListItemsCount(): Int? {
        return wishListDao.getWishListItemsCount()
    }

    override suspend fun getTotalWishListPrice(): Double? {
        return wishListDao.getTotalWishListPrice()
    }

    override suspend fun delete(wishListItem: WishListItem) {
        return wishListDao.delete(wishListItem)
    }

    override suspend fun deleteAll() {
        return wishListDao.deleteAll()
    }

}