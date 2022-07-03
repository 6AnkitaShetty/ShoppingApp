package com.example.shoppingapp.features.home.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class WishListItem(
    @PrimaryKey
    val productId: Int = -1,
    val productName: String? = null,
    val productImage: String? = null,
    val price: Double? = 0.0
) : Serializable