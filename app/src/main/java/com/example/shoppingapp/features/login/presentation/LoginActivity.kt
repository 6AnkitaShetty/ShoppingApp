package com.example.shoppingapp.features.login.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.shoppingapp.features.home.presentation.MainActivity
import com.example.shoppingapp.databinding.ActivityLoginBinding
import com.example.shoppingapp.util.Resource
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.viewModel = loginViewModel

        if (loginViewModel.isLoggedIn) {
            navigateToMainActivity()
        }
        binding.btnSignIn.setOnClickListener {
            loginViewModel.login(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            )
        }
        setUpObservers()
    }

    private fun setUpObservers() {
        loginViewModel.loginResult.observe(this, Observer {
            val loginResult = it ?: return@Observer

            when (loginResult) {
                is Resource.Success -> {
                    loginViewModel.saveLoginStatus(true)
                    var name = binding.etEmail.text.toString()
                    if (name.contains("@")) {
                        name = name.split("@")[0]
                    }
                    loginViewModel.saveUserCredentials(name)
                    navigateToMainActivity()
                }
                is Resource.Error -> {
                    val errorMsg = loginResult.message?.let { message -> getString(message) } ?: ""
                    Snackbar.make(binding.root, errorMsg, Snackbar.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun navigateToMainActivity() {
        val i = Intent(
            this,
            MainActivity::class.java
        )
        startActivity(i)
        finish()
    }
}