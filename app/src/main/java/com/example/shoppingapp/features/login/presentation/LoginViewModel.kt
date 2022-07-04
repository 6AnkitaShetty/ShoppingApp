package com.example.shoppingapp.features.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.features.login.domain.repository.DataStoreRepository
import com.example.shoppingapp.features.login.domain.usecase.LoginUseCase
import com.example.shoppingapp.util.Constants
import com.example.shoppingapp.util.Constants.IS_LOGIN
import com.example.shoppingapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val repository: DataStoreRepository
) : ViewModel() {

    val emailAddress = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    private val loginResultLiveData = MutableLiveData<Resource<Int>>()
    val loginResult: LiveData<Resource<Int>> = loginResultLiveData

    fun login(userName: String, pass: String) {
        val result = loginUseCase.invoke(emailAddress.value ?: userName, password.value ?: pass)
        loginResultLiveData.postValue(result)
    }

    val isLoggedIn: Boolean = runBlocking {
        repository.getBoolean(IS_LOGIN) ?: false
    }

    fun saveLoginStatus(loggedIn: Boolean) {
        viewModelScope.launch {
            repository.putBoolean(IS_LOGIN, loggedIn)
        }
    }

    fun saveUserCredentials(name: String) {
        viewModelScope.launch {
            repository.putString(Constants.USERNAME, name)
        }
    }
}