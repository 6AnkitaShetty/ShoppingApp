package com.example.shoppingapp.features.home.domain.repository

import com.example.shoppingapp.features.home.data.FakeDataUtil
import com.example.shoppingapp.features.home.data.local.model.CartItem

class FakeCartRepository : CartRepository {
    private val cartItems = mutableListOf<CartItem>()
    override suspend fun getCartItems(): List<CartItem> {
        cartItems.add(FakeDataUtil.getCartItem())
        return cartItems
    }

    override suspend fun getCartItemById(id: Int): CartItem? {
        return cartItems.firstOrNull { it.productId == id }
    }

    override suspend fun insertItemToCart(cartItem: CartItem): Long {
        cartItems.add(cartItem)
        return 1
    }

    override suspend fun getCartItemsCount(): Int {
        return cartItems.size
    }

    override suspend fun getTotalCartPrice(): Double {
        return cartItems.sumOf { it.price ?: 0.0 }
    }

    override suspend fun delete(cartItem: CartItem) {
        cartItems.remove(cartItem)
    }

    override suspend fun deleteAll() {
        cartItems.clear()
    }
}