package com.example.shoppingapp.features.home.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.features.home.data.remote.model.Product
import com.example.shoppingapp.features.home.domain.usecase.CartUseCases
import com.example.shoppingapp.features.home.domain.usecase.WishListUseCases
import com.example.shoppingapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val cartUseCases: CartUseCases,
    private val wishListUseCases: WishListUseCases
) : ViewModel() {

    private val productDetailLiveData = MutableLiveData<Resource<Product>>()
    val productDetail: LiveData<Resource<Product>> = productDetailLiveData

    private val isProductWishListedLiveData = MutableLiveData(false)
    val isProductWishListed: LiveData<Boolean> = isProductWishListedLiveData

    private val fetchCartCountLiveData = MutableLiveData<Boolean>()
    val fetchCartCount: LiveData<Boolean> = fetchCartCountLiveData

    private val fetchWishListCountLiveData = MutableLiveData<Boolean>()
    val fetchWishListCount: LiveData<Boolean> = fetchWishListCountLiveData


    fun fetchProductDetails(product:Product) {
        viewModelScope.launch {
            productDetailLiveData.postValue(Resource.Success(product))
            isProductWishListedLiveData.postValue(product.isWishListed)
        }
    }

    fun addToCart(product: Product) {
        viewModelScope.launch {
            cartUseCases.addItemToCartUseCase(product)
            fetchCartCountLiveData.postValue(true)
        }
    }

    fun addToWishList(product: Product, isMainProduct: Boolean) {
        viewModelScope.launch {
            wishListUseCases.addItemToWishListUseCase(product)
            fetchWishListCountLiveData.postValue(true)
        }

        if (isMainProduct) {
            val isWishListed: Boolean = isProductWishListedLiveData.value ?: false
            isProductWishListedLiveData.postValue(!isWishListed)
        }
    }

}