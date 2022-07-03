package com.example.shoppingapp.features.home.data.local

import androidx.room.*
import com.example.shoppingapp.features.home.data.local.model.WishListItem

@Dao
interface WishListDao {
    @Query("SELECT * FROM WishListItem")
    suspend fun getWishListItems(): List<WishListItem>

    @Query("SELECT * FROM WishListItem WHERE productId=:id")
    suspend fun getWishListItemById(id :Int) : WishListItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemToWishList(wishListItem : WishListItem) : Long?

    @Query("SELECT COUNT(*) FROM WishListItem")
    suspend fun getWishListItemsCount(): Int?

    @Query("SELECT SUM(price) FROM WishListItem")
    suspend fun getTotalWishListPrice(): Double?

    @Delete
    suspend fun delete(wishListItem: WishListItem)

    @Query("DELETE FROM WishListItem")
    suspend fun deleteAll()
}