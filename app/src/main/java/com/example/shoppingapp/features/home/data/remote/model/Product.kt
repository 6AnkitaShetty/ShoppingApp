package com.example.shoppingapp.features.home.data.remote.model

import java.io.Serializable

data class Product(
    val USPs: List<String>?,
    val availabilityState: Int?,
    val coolbluesChoiceInformationTitle: String?,
    val listPriceExVat: Double?,
    val listPriceIncVat: Int?,
    val nextDayDelivery: Boolean?,
    val productId: Int,
    val productImage: String,
    val productName: String,
    val promoIcon: PromoIcon?,
    val reviewInformation: ReviewInformation?,
    val salesPriceIncVat: Double?,
    var isWishListed: Boolean = false
) : Serializable