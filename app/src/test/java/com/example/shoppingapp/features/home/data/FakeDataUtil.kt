package com.example.shoppingapp.features.home.data

import com.example.shoppingapp.features.home.data.local.model.CartItem
import com.example.shoppingapp.features.home.data.local.model.WishListItem
import com.example.shoppingapp.features.home.data.remote.model.Product
import com.example.shoppingapp.features.home.data.remote.model.PromoIcon
import com.example.shoppingapp.features.home.data.remote.model.ReviewInformation
import com.example.shoppingapp.features.home.data.remote.model.ReviewSummary
import com.example.shoppingapp.util.Resource

object FakeDataUtil {

    fun getFakeProductsResponse(): Resource<List<Product>> {
        val products = getFakeProducts().toList()
        return Resource.Success(products)
    }

    fun getFakeCartItemsResponse(): List<CartItem> {
        return getFakeCartItems().toList()
    }

    fun getFakeWishListItemsResponse():List<WishListItem> {
        return getFakeWishListItems().toList()
    }

    fun getFakeCartItems(): MutableList<CartItem> {
        val cartItemList: MutableList<CartItem> = arrayListOf()
        val cart1 = CartItem(
            productId = 793652,
            productName = "Apple iPhone X 256GB Zilver",
            productImage = "https://image.coolblue.nl/300x750/products/984921",
            count = 1,
            price = 1279.0
        )
        val cart2 = CartItem(
            productId = 608152,
            productName = "Apple Usb-C to Digital AV Adapter",
            productImage = "https://image.coolblue.nl/300x750/products/406340",
            count = 1,
            price = 76.0
        )
        cartItemList.add(cart1)
        cartItemList.add(cart2)
        return cartItemList
    }

    fun getFakeWishListItems(): MutableList<WishListItem> {
        val wishListItemList: MutableList<WishListItem> = arrayListOf()
        val wishListItem1 = WishListItem(
            productId = 793652,
            productName = "Apple iPhone X 256GB Zilver",
            productImage = "https://image.coolblue.nl/300x750/products/984921",
            price = 1279.0
        )
        val wishListItem2 = WishListItem(
            productId = 608152,
            productName = "Apple Usb-C to Digital AV Adapter",
            productImage = "https://image.coolblue.nl/300x750/products/406340",
            price = 76.0
        )
        wishListItemList.add(wishListItem1)
        wishListItemList.add(wishListItem2)
        return wishListItemList
    }

    fun getFakeProducts(): MutableList<Product> {
        val productList: MutableList<Product> = arrayListOf()
        val product1 = Product(
            productId = 793652,
            productName = "Apple iPhone X 256GB Zilver",
            reviewInformation = ReviewInformation(
                reviews = listOf(),
                reviewSummary = ReviewSummary(reviewAverage = 9.2, reviewCount = 209)
            ),
            USPs = listOf(
                "256 GB opslagcapaciteit",
                "5,8 inch Retina HD scherm",
                "iOS 11"
            ),
            availabilityState = 2,
            salesPriceIncVat = 1279.0,
            productImage = "https://image.coolblue.nl/300x750/products/984921",
            nextDayDelivery = true,
            isWishListed = false,
            listPriceExVat = 0.0,
            listPriceIncVat = 0,
            promoIcon = PromoIcon("", ""),
            coolbluesChoiceInformationTitle = ""
        )
        val product2 = Product(
            productId = 608152,
            productName = "Apple Usb-C to Digital AV Adapter",
            reviewInformation = ReviewInformation(
                reviews = listOf(),
                reviewSummary = ReviewSummary(reviewAverage = 9.6, reviewCount = 41)
            ),
            USPs = listOf(
                "usb c/Thunderbolt 3 (male)",
                "HDMI, usb c en usb  (female)",
                "0,2 meter"
            ),
            availabilityState = 2,
            salesPriceIncVat = 76.0,
            productImage = "https://image.coolblue.nl/300x750/products/406340",
            nextDayDelivery = true,
            isWishListed = false,
            listPriceExVat = 0.0,
            listPriceIncVat = 0,
            promoIcon = PromoIcon("", ""),
            coolbluesChoiceInformationTitle = ""
        )
        productList.add(product1)
        productList.add(product2)
        return productList
    }

    fun getCartItem(): CartItem {
        return CartItem(
            productId = 793652,
            productName = "Apple iPhone X 256GB Zilver",
            productImage = "https://image.coolblue.nl/300x750/products/984921",
            count = 1,
            price = 1279.0
        )
    }

    fun getWishListItem(): WishListItem {
        return WishListItem(
            productId = 793652,
            productName = "Apple iPhone X 256GB Zilver",
            productImage = "https://image.coolblue.nl/300x750/products/984921",
            price = 1279.0
        )
    }

    fun getProduct(): Product {
        return Product(
            productId = 608152,
            productName = "Apple Usb-C to Digital AV Adapter",
            reviewInformation = ReviewInformation(
                reviews = listOf(),
                reviewSummary = ReviewSummary(reviewAverage = 9.6, reviewCount = 41)
            ),
            USPs = listOf(
                "usb c/Thunderbolt 3 (male)",
                "HDMI, usb c en usb  (female)",
                "0,2 meter"
            ),
            availabilityState = 2,
            salesPriceIncVat = 76.0,
            productImage = "https://image.coolblue.nl/300x750/products/406340",
            nextDayDelivery = true,
            isWishListed = false,
            listPriceExVat = 0.0,
            listPriceIncVat = 0,
            promoIcon = PromoIcon("", ""),
            coolbluesChoiceInformationTitle = ""
        )
    }

}