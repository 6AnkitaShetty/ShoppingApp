package com.example.shoppingapp.features.home.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.shoppingapp.features.home.data.FakeDataUtil
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
class AddItemToWishListUseCaseTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private var fakeWishListRepository = FakeWishListRepository()
    private lateinit var addItemToWishListUseCase: AddItemToWishListUseCase

    @Before
    fun setUp() {
        addItemToWishListUseCase = AddItemToWishListUseCase(fakeWishListRepository)
    }

    @Test
    fun addItemToWishList_Returns_Success() {
        coroutineRule.runBlockingTest {
            val result = addItemToWishListUseCase.invoke(FakeDataUtil.getProduct())
            TestCase.assertEquals(result, 1L)
        }
    }

}