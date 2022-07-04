package com.example.shoppingapp.features.home.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.shoppingapp.features.home.data.FakeDataUtil
import com.example.shoppingapp.features.home.domain.repository.FakeCartRepository
import com.example.shoppingapp.features.home.domain.usecase.*
import com.example.shoppingapp.features.home.presentation.cart.CartViewModel
import com.example.shoppingapp.util.MainCoroutineRule
import com.example.shoppingapp.util.runBlockingTest
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CartViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var cartUseCases: CartUseCases

    @Mock
    private lateinit var fakeCartRepository: FakeCartRepository

    private lateinit var viewModel: CartViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        cartUseCases = CartUseCases(
            GetCartItemsUseCase(fakeCartRepository),
            GetCartItemByIdUseCase(fakeCartRepository),
            AddItemToCartUseCase(fakeCartRepository),
            DeleteCartItemUseCase(fakeCartRepository),
            GetCartItemCountUseCase(fakeCartRepository),
            GetTotalCartPriceUseCase(fakeCartRepository),
            AddWishListItemToCartUseCase(fakeCartRepository),
            DeleteAllItemsInCartUseCase(fakeCartRepository)
        )
        viewModel = CartViewModel(
            cartUseCases
        )

    }

    @Test
    fun fetchCartItems_success() {
        coroutineRule.runBlockingTest {
            whenever(fakeCartRepository.getCartItems())
                .thenAnswer { FakeDataUtil.getFakeCartItemsResponse() }
            viewModel.getCartItems()
            val response = viewModel.cartItems.value
            TestCase.assertEquals(response?.data?.size, 2)
        }
    }

    @Test
    fun fetchCount_success() {
        coroutineRule.runBlockingTest {
            whenever(fakeCartRepository.getCartItemsCount())
                .thenAnswer { (FakeDataUtil.getCartItem().count) }
            viewModel.fetchCount()
            val response = viewModel.cartCount.value
            TestCase.assertEquals(response, 1)
        }
    }

    @Test
    fun fetchTotalCartPrice_success() {
        coroutineRule.runBlockingTest {
            whenever(fakeCartRepository.getCartItemsCount())
                .thenAnswer { (FakeDataUtil.getCartItem().count) }
            whenever(fakeCartRepository.getTotalCartPrice())
                .thenAnswer {
                    FakeDataUtil.getCartItem().price
                }
            viewModel.fetchTotalCartPrice()
            val response = viewModel.totalPrice.value
            TestCase.assertEquals(response, 1279.0)
        }
    }

}