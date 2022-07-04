package com.example.shoppingapp.features.home.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.shoppingapp.features.home.data.FakeDataUtil
import com.example.shoppingapp.features.home.domain.repository.FakeCartRepository
import com.example.shoppingapp.features.home.domain.repository.FakeWishListRepository
import com.example.shoppingapp.features.home.domain.usecase.*
import com.example.shoppingapp.features.home.presentation.wishlist.WishListViewModel
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
class WishListViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var cartUseCases: CartUseCases
    private lateinit var wishListUseCases: WishListUseCases

    @Mock
    private lateinit var fakeWishListRepository: FakeWishListRepository

    @Mock
    private lateinit var fakeCartRepository: FakeCartRepository

    private lateinit var viewModel: WishListViewModel

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
        wishListUseCases = WishListUseCases(
            GetWishListItemsUseCase(fakeWishListRepository),
            GetWishListItemCountUseCase(fakeWishListRepository),
            AddItemToWishListUseCase(fakeWishListRepository),
            GetWishListItemByIdUseCase(fakeWishListRepository),
            DeleteWishListItemUseCase(fakeWishListRepository)
        )
        viewModel = WishListViewModel(
            wishListUseCases,
            cartUseCases
        )
    }

    @Test
    fun fetchWishListItems_success() {
        coroutineRule.runBlockingTest {
            whenever(fakeWishListRepository.getWishListItems())
                .thenAnswer { FakeDataUtil.getFakeWishListItemsResponse() }
            viewModel.getWishListItems()
            val response = viewModel.wishListItems.value
            TestCase.assertEquals(response?.data?.size, 2)
        }

    }
}