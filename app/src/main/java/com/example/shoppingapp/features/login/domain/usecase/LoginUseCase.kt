package com.example.shoppingapp.features.login.domain.usecase

import com.example.shoppingapp.features.login.domain.repository.LoginRepository
import com.example.shoppingapp.util.Resource

class LoginUseCase(
    private val loginRepository: LoginRepository
) {
    operator fun invoke(username: String, password: String): Resource<Int> {
        return loginRepository.onLoginClicked(username, password)
    }
}