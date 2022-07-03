package com.example.shoppingapp.features.home.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class CartItem(
    @PrimaryKey
    val productId: Int = -1,
    val productName: String? = null,
    val productImage: String? = null,
    val count: Int? = 0,
    val price: Double? = 0.0
) : Serializable