package com.example.shoppingapp.features.home.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.shoppingapp.features.home.domain.repository.FakeCartRepository
import com.example.shoppingapp.util.MainCoroutineRule
import com.example.shoppingapp.util.runBlockingTest
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetCartItemsUseCaseTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private var fakeCartRepository = FakeCartRepository()
    private lateinit var getCartItemsUseCase: GetCartItemsUseCase

    @Before
    fun setUp() {
        getCartItemsUseCase = GetCartItemsUseCase(fakeCartRepository)
    }

    @Test
    fun getCartItems_Returns_Success() {
        coroutineRule.runBlockingTest {
            val list = getCartItemsUseCase.invoke()
            TestCase.assertNotNull(list)
            TestCase.assertEquals(list.size, 1)
        }
    }
}