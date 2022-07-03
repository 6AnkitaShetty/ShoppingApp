package com.example.shoppingapp.features.home.presentation.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.features.home.data.local.model.CartItem
import com.example.shoppingapp.features.home.domain.usecase.CartUseCases
import com.example.shoppingapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartUseCases: CartUseCases
) : ViewModel() {

    private val cartItemsLiveData = MutableLiveData<Resource<List<CartItem>>>()
    val cartItems: LiveData<Resource<List<CartItem>>> = cartItemsLiveData

    private val totalPriceLiveData = MutableLiveData<Double>()
    val totalPrice: LiveData<Double> = totalPriceLiveData

    private val cartCountLiveData = MutableLiveData(1)
    val cartCount: LiveData<Int> = cartCountLiveData

    private val triggerCheckOutClickLiveData = MutableLiveData<Boolean>()
    val triggerCheckOutClick: LiveData<Boolean> = triggerCheckOutClickLiveData

    init {
        getCartItems()
    }

    private fun getCartItems() {
        viewModelScope.launch {
            val cartItems = cartUseCases.getCartItemsUseCase.invoke()
            cartItemsLiveData.postValue(Resource.Success(cartItems))

            fetchCount()
            fetchTotalCartPrice()
        }
    }

    fun deleteItem(cartItem: CartItem) {
        viewModelScope.launch {
            cartUseCases.deleteCartItemUseCase.invoke(cartItem)
            getCartItems()
        }
    }

    private fun fetchCount() {
        viewModelScope.launch {
            val count = cartUseCases.cartItemCountUseCase() ?: 0
            cartCountLiveData.postValue(count)
        }
    }

    private fun fetchTotalCartPrice() {
        viewModelScope.launch {
            val count = cartUseCases.cartItemCountUseCase() ?: 0
            val totalPrice = cartUseCases.cartPriceUseCase()  ?: 0.0
            val price = count * totalPrice
            totalPriceLiveData.postValue(price)
        }
    }

    fun onCheckOutClick() {
        triggerCheckOutClickLiveData.postValue(true)
    }
}