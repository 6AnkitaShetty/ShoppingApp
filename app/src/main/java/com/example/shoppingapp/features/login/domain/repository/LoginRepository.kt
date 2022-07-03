package com.example.shoppingapp.features.login.domain.repository

import com.example.shoppingapp.util.Resource

interface LoginRepository {
    fun onLoginClicked(username: String, password: String): Resource<Int>
}