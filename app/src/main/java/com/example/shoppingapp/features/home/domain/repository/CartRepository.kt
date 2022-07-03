package com.example.shoppingapp.features.home.domain.repository

import com.example.shoppingapp.features.home.data.local.model.CartItem

interface CartRepository {
    suspend fun getCartItems(): List<CartItem>

    suspend fun getCartItemById(id: Int): CartItem?

    suspend fun insertItemToCart(cartItem : CartItem) : Long?

    suspend fun getCartItemsCount(): Int?

    suspend fun getTotalCartPrice(): Double?

    suspend fun delete(cartItem: CartItem)

    suspend fun deleteAll()
}