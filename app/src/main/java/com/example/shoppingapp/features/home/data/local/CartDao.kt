package com.example.shoppingapp.features.home.data.local

import androidx.room.*
import com.example.shoppingapp.features.home.data.local.model.CartItem

@Dao
interface CartDao {
    @Query("SELECT * FROM CartItem")
    suspend fun getCartItems(): List<CartItem>

    @Query("SELECT * FROM CartItem WHERE productId=:id")
    suspend fun getCartItemById(id :Int) : CartItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemToCart(cartItem : CartItem) : Long?

    @Query("SELECT SUM(count) FROM CartItem")
    suspend fun getCartItemsCount(): Int?

    @Query("SELECT SUM(price) FROM CartItem")
    suspend fun getTotalCartPrice(): Double?

    @Delete
    suspend fun delete(cartItem: CartItem)

    @Query("DELETE FROM CartItem")
    suspend fun deleteAll()
}