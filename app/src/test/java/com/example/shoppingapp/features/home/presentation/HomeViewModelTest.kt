package com.example.shoppingapp.features.home.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.shoppingapp.features.home.data.FakeDataUtil
import com.example.shoppingapp.features.home.domain.repository.FakeCartRepository
import com.example.shoppingapp.features.home.domain.repository.FakeWishListRepository
import com.example.shoppingapp.features.home.domain.repository.ProductsRepository
import com.example.shoppingapp.features.home.domain.usecase.*
import com.example.shoppingapp.features.home.presentation.home.HomeViewModel
import com.example.shoppingapp.features.login.domain.repository.DataStoreRepository
import com.example.shoppingapp.util.MainCoroutineRule
import com.example.shoppingapp.util.NetworkHelper
import com.example.shoppingapp.util.provideFakeCoroutinesDispatcherProvider
import com.example.shoppingapp.util.runBlockingTest
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.whenever
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
class HomeViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var cartUseCases: CartUseCases
    private lateinit var wishListUseCases: WishListUseCases
    private lateinit var productsUseCase: FetchProductsUseCase

    @Mock
    private lateinit var fakeWishListRepository: FakeWishListRepository

    @Mock
    private lateinit var fakeCartRepository: FakeCartRepository

    @Mock
    private lateinit var productsRepository: ProductsRepository

    @Mock
    private lateinit var dataStoreRepository: DataStoreRepository

    private lateinit var viewModel: HomeViewModel
    private val testDispatcher = coroutineRule.testDispatcher

    @Mock
    private lateinit var networkHelper: NetworkHelper

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
        productsUseCase = FetchProductsUseCase(productsRepository, wishListUseCases)
        viewModel = HomeViewModel(
            networkHelper,
            productsUseCase,
            wishListUseCases,
            cartUseCases,
            dataStoreRepository,
            coroutinesDispatcherProvider = provideFakeCoroutinesDispatcherProvider(testDispatcher)
        )
    }

    @Test
    fun fetchProducts_success() {
        coroutineRule.runBlockingTest {
            whenever(networkHelper.isNetworkConnected())
                .thenReturn(true)
            // Stub repository with fake stations
            whenever(productsRepository.getProducts())
                .thenAnswer { (FakeDataUtil.getFakeProductsResponse()) }

            //When
            viewModel.fetchProducts()

            //then
            assertThat(viewModel.categoryProducts.value).isNotNull()
            val products = viewModel.categoryProducts.value?.data

            assertThat(products?.isNotEmpty())

            // compare the response with fake list
            assertThat(products).hasSize(FakeDataUtil.getFakeProducts().size)

            // compare the data and also order
            assertThat(products).containsExactlyElementsIn(
                FakeDataUtil.getFakeProducts()
            ).inOrder()
        }
    }

}