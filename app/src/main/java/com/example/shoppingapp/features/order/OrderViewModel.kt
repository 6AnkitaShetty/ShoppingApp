package com.example.shoppingapp.features.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.features.home.domain.usecase.CartUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(private val cartUseCases: CartUseCases) : ViewModel() {

    fun deleteCartItems() {
        viewModelScope.launch {
            cartUseCases.deleteAllItemsInCartUseCase.invoke()
        }
    }
}