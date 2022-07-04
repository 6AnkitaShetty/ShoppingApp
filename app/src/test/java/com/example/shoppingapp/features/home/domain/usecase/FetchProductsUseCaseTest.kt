package com.example.shoppingapp.features.home.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.shoppingapp.features.home.domain.repository.FakeProductsRepository
import com.example.shoppingapp.features.home.domain.repository.FakeWishListRepository
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
class FetchProductsUseCaseTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()
    private var fakeWishListRepository = FakeWishListRepository()
    private lateinit var fakeProductsRepository: FakeProductsRepository
    private lateinit var fetchProductsUseCase: FetchProductsUseCase
    private val wishListUseCases = WishListUseCases(
        GetWishListItemsUseCase(fakeWishListRepository),
        GetWishListItemCountUseCase(fakeWishListRepository),
        AddItemToWishListUseCase(fakeWishListRepository),
        GetWishListItemByIdUseCase(fakeWishListRepository),
        DeleteWishListItemUseCase(fakeWishListRepository)
    )

    @Before
    fun setUp() {
        fakeProductsRepository = FakeProductsRepository()
        fetchProductsUseCase = FetchProductsUseCase(fakeProductsRepository, wishListUseCases)
    }

    @Test
    fun getProducts_Returns_Success() {
        coroutineRule.runBlockingTest {
            val products = fetchProductsUseCase.invoke()
            TestCase.assertNotNull(products)
            TestCase.assertEquals(products.data?.size, 2)
        }
    }
}