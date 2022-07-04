package com.example.shoppingapp.features.home.presentation.wishlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.features.home.data.local.model.WishListItem
import com.example.shoppingapp.features.home.domain.usecase.CartUseCases
import com.example.shoppingapp.features.home.domain.usecase.WishListUseCases
import com.example.shoppingapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishListViewModel @Inject constructor(
    private val wishListUseCases: WishListUseCases,
    private val cartUseCases: CartUseCases
) : ViewModel() {

    private val wishListItemsLiveData = MutableLiveData<Resource<List<WishListItem>>>()
    val wishListItems: LiveData<Resource<List<WishListItem>>> = wishListItemsLiveData

    private val fetchCartCountLiveData = MutableLiveData<Boolean>()
    val fetchCartCount: LiveData<Boolean> = fetchCartCountLiveData

    private val wishListItemsSizeLiveData = MutableLiveData<Int>()
    val wishListItemsSize: LiveData<Int> = wishListItemsSizeLiveData

    init {
        getWishListItems()
    }

    fun getWishListItems() {
        viewModelScope.launch {
            val wishListItems = wishListUseCases.getWishListItemsUseCase.invoke()
            wishListItemsLiveData.postValue(Resource.Success(wishListItems))
            wishListItemsSizeLiveData.postValue(wishListItems.size)
        }
    }

    fun deleteItem(wishListItem: WishListItem) {
        viewModelScope.launch {
            wishListUseCases.deleteWishListItemUseCase.invoke(wishListItem)
            getWishListItems()
        }
    }

    fun addItemToCart(wishListItem: WishListItem) {
        viewModelScope.launch {
            cartUseCases.addWishListItemToCartUseCase.invoke(wishListItem)
            fetchCartCountLiveData.postValue(true)
        }
    }
}