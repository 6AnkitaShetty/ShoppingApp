package com.example.shoppingapp.features.home.presentation.home

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.R
import com.example.shoppingapp.di.CoroutinesDispatcherProvider
import com.example.shoppingapp.features.home.data.remote.model.Product
import com.example.shoppingapp.features.home.domain.usecase.CartUseCases
import com.example.shoppingapp.features.home.domain.usecase.FetchProductsUseCase
import com.example.shoppingapp.features.home.domain.usecase.WishListUseCases
import com.example.shoppingapp.features.login.domain.repository.DataStoreRepository
import com.example.shoppingapp.util.Constants
import com.example.shoppingapp.util.NetworkHelper
import com.example.shoppingapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val productUseCase: FetchProductsUseCase,
    private val wishListUseCases: WishListUseCases,
    private val cartUseCases: CartUseCases,
    private val repository: DataStoreRepository,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    private val categoryProductsLiveData = MutableLiveData<Resource<List<Product>>>()
    val categoryProducts: LiveData<Resource<List<Product>>> = categoryProductsLiveData

    private val fetchWishListCountLiveData = MutableLiveData<Boolean>()
    val fetchWishListCount: LiveData<Boolean> = fetchWishListCountLiveData

    private val fetchCartCountLiveData = MutableLiveData<Boolean>()
    val fetchCartCount: LiveData<Boolean> = fetchCartCountLiveData

    private val loadingLiveData = MutableLiveData(View.GONE)
    val loading: LiveData<Int> = loadingLiveData

    private val triggerLogOutClickLiveData = MutableLiveData<Boolean>()
    val triggerLogOutClick: LiveData<Boolean> = triggerLogOutClickLiveData

    fun fetchProducts() {
        viewModelScope.launch(coroutinesDispatcherProvider.io) {
            loadingLiveData.postValue(View.VISIBLE)
            try {
                if (networkHelper.isNetworkConnected()) {
                    val result = productUseCase.invoke()
                    loadingLiveData.postValue(View.GONE)
                    categoryProductsLiveData.postValue(result)
                } else {
                    categoryProductsLiveData.postValue(Resource.Error(R.string.no_internet_message))
                }
            } catch (e: Exception) {
                categoryProductsLiveData.postValue(Resource.Error(R.string.error_message))
            }
        }
    }

    fun addItemToWishList(product: Product) {
        viewModelScope.launch {
            wishListUseCases.addItemToWishListUseCase(product)
            fetchWishListCountLiveData.postValue(true)
        }
    }

    fun addItemToCart(product: Product) {
        viewModelScope.launch {
            cartUseCases.addItemToCartUseCase(product)
            fetchCartCountLiveData.postValue(true)
        }
    }

    private fun saveLoginStatus(loggedIn: Boolean) {
        viewModelScope.launch {
            repository.putBoolean(Constants.IS_LOGIN, loggedIn)
        }
    }

    private fun saveUserCredentials(name: String) {
        viewModelScope.launch {
            repository.putString(Constants.USERNAME, name)
        }
    }

    val userName: Flow<String>? = runBlocking {
        repository.getString(Constants.USERNAME)
    }

    fun logout() {
        saveLoginStatus(false)
        saveUserCredentials("")
        triggerLogOutClickLiveData.postValue(true)
    }
}