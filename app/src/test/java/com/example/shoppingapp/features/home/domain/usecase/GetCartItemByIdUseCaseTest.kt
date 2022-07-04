package com.example.shoppingapp.features.home.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.shoppingapp.features.home.data.FakeDataUtil
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
class GetCartItemByIdUseCaseTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private var fakeCartRepository = FakeCartRepository()
    private lateinit var getCartItemByIdUseCase: GetCartItemByIdUseCase

    @Before
    fun setUp() {
        getCartItemByIdUseCase = GetCartItemByIdUseCase(fakeCartRepository)
    }

    @Test
    fun getCartItemById_Returns_Success() {
        coroutineRule.runBlockingTest {
            val id = 793652
            fakeCartRepository.insertItemToCart(FakeDataUtil.getCartItem())
            val item = getCartItemByIdUseCase.invoke(id)
            TestCase.assertEquals(item?.productId, id)
        }
    }
}