package com.example.shoppingapp.features.home.data.repository

import com.example.shoppingapp.features.home.data.local.CartDao
import com.example.shoppingapp.features.home.data.local.model.CartItem
import com.example.shoppingapp.features.home.domain.repository.CartRepository

class CartRepositoryImpl(
    private val cartDao: CartDao
) : CartRepository {
    override suspend fun getCartItems(): List<CartItem> {
        return cartDao.getCartItems()
    }

    override suspend fun getCartItemById(id: Int): CartItem? {
        return cartDao.getCartItemById(id)
    }

    override suspend fun insertItemToCart(cartItem: CartItem): Long? {
        return cartDao.insertItemToCart(cartItem)
    }

    override suspend fun getCartItemsCount(): Int? {
        return cartDao.getCartItemsCount()
    }

    override suspend fun getTotalCartPrice(): Double? {
        return cartDao.getTotalCartPrice()
    }

    override suspend fun delete(cartItem: CartItem) {
        return cartDao.delete(cartItem)
    }

    override suspend fun deleteAll() {
        return cartDao.deleteAll()
    }
}