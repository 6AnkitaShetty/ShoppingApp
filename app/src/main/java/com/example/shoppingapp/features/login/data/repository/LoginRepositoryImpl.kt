package com.example.shoppingapp.features.login.data.repository

import androidx.core.util.PatternsCompat
import com.example.shoppingapp.R
import com.example.shoppingapp.features.login.domain.repository.LoginRepository
import com.example.shoppingapp.util.Resource

class LoginRepositoryImpl : LoginRepository {

    override fun onLoginClicked(username: String, password: String): Resource<Int> {
        return if (!isUserNameValid(username)) {
            Resource.Error(R.string.invalid_email)
        } else if (!isPasswordValid(password)) {
            Resource.Error(R.string.invalid_password)
        } else {
            Resource.Success(R.string.welcome)
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        if (username.contains("@")) {
            return PatternsCompat.EMAIL_ADDRESS.matcher(username).matches()
        }
        return false
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}