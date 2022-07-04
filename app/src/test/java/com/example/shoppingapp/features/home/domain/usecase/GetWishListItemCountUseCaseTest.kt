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
class GetWishListItemCountUseCaseTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private var fakeWishListRepository = FakeWishListRepository()
    private lateinit var getWishListItemCountUseCase: GetWishListItemCountUseCase

    @Before
    fun setUp() {
        getWishListItemCountUseCase = GetWishListItemCountUseCase(fakeWishListRepository)
    }

    @Test
    fun getWishListItemCount_Returns_Success() {
        coroutineRule.runBlockingTest {
            val count = getWishListItemCountUseCase.invoke()
            TestCase.assertEquals(count, 0)
        }
    }

    @Test
    fun getWishListItemCount_notEmpty() {
        coroutineRule.runBlockingTest {
            // Given
            fakeWishListRepository.insertItemToWishList(FakeDataUtil.getWishListItem())

            // When
            val count = getWishListItemCountUseCase.invoke()

            // Then
            TestCase.assertEquals(count, 1)
        }
    }
}