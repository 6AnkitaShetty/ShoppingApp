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
class DeleteAllItemsInCartUseCaseTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private var fakeCartRepository = FakeCartRepository()
    private lateinit var deleteAllItemsInCartUseCase: DeleteAllItemsInCartUseCase

    @Before
    fun setUp() {
        deleteAllItemsInCartUseCase = DeleteAllItemsInCartUseCase(fakeCartRepository)
    }

    @Test
    fun deleteAllItemsInCart_Returns_Success() {
        coroutineRule.runBlockingTest {
            val result = fakeCartRepository.getCartItems()
            deleteAllItemsInCartUseCase.invoke()
            TestCase.assertEquals(result.size, 0)
        }
    }
}