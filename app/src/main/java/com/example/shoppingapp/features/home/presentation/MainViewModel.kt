package com.example.shoppingapp.features.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.features.home.domain.usecase.CartUseCases
import com.example.shoppingapp.features.home.domain.usecase.WishListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val cartUseCases: CartUseCases,
    private val wishListUseCases: WishListUseCases
) : ViewModel() {

    private val cartCountLiveData = MutableLiveData<Int>()
    val cartCount: LiveData<Int> = cartCountLiveData

    private val wishListCountLiveData = MutableLiveData<Int>()
    val wishListCount: LiveData<Int> = wishListCountLiveData

    fun fetchCartCount() {
        viewModelScope.launch {
            val count = cartUseCases.cartItemCountUseCase.invoke()
            cartCountLiveData.postValue(count ?: 0)
        }
    }

    fun fetchWishListCount() {
        viewModelScope.launch {
            val count = wishListUseCases.getWishListItemCountUseCase.invoke()
            wishListCountLiveData.postValue(count ?: 0)
        }
    }
}