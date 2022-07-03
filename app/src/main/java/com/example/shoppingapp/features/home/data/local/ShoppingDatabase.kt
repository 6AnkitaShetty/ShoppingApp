package com.example.shoppingapp.features.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shoppingapp.features.home.data.local.model.CartItem
import com.example.shoppingapp.features.home.data.local.model.WishListItem

@Database(entities = [CartItem::class, WishListItem::class], version = 1)
abstract class ShoppingDatabase : RoomDatabase() {
    abstract val cartDao: CartDao
    abstract val wishlistDao: WishListDao
}