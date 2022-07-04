package com.example.shoppingapp.features.login.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.shoppingapp.features.login.domain.usecase.LoginUseCase
import com.example.shoppingapp.features.login.repository.FakeDataStoreRepository
import com.example.shoppingapp.features.login.repository.FakeLoginRepository
import com.example.shoppingapp.util.MainCoroutineRule
import com.example.shoppingapp.util.runBlockingTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import com.example.shoppingapp.R
import junit.framework.TestCase

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var viewModel: LoginViewModel
    private lateinit var loginUseCase: LoginUseCase

    @Mock
    private lateinit var dataStoreRepository: FakeDataStoreRepository

    private var fakeLoginRepository = FakeLoginRepository()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        loginUseCase = LoginUseCase(fakeLoginRepository)
        viewModel = LoginViewModel(
            loginUseCase,
            dataStoreRepository
        )
    }

    @Test
    fun login_fail_invalid_username() {
        coroutineRule.runBlockingTest {
            val email = "abc"
            val password = "123"
            viewModel.login(email, password)
            val response = viewModel.loginResult.value
            TestCase.assertEquals(response?.message, R.string.invalid_email)
        }
    }

    @Test
    fun login_fail_invalid_password() {
        coroutineRule.runBlockingTest {
            val email = "abc@fjh.com"
            val password = "123"
            viewModel.login(email, password)
            val response = viewModel.loginResult.value
            TestCase.assertEquals(response?.message, R.string.invalid_password)
        }
    }

    @Test
    fun login_success() {
        coroutineRule.runBlockingTest {
            val email = "abc@fjh.com"
            val password = "123456"
            viewModel.login(email, password)

            val response = viewModel.loginResult.value
            TestCase.assertEquals(response?.data, R.string.welcome)
        }
    }
}