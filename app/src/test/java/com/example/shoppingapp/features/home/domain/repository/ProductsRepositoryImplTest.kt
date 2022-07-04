package com.example.shoppingapp.features.home.domain.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.shoppingapp.features.home.data.remote.ShoppingApiService
import com.example.shoppingapp.features.home.data.repository.ProductsRepositoryImpl
import com.example.shoppingapp.util.MainCoroutineRule
import com.example.shoppingapp.util.MockWebServerBaseTest
import com.example.shoppingapp.util.Resource
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.net.HttpURLConnection
import com.example.shoppingapp.R

@ExperimentalCoroutinesApi
@Config(sdk = [30])
@RunWith(
    RobolectricTestRunner::class
)
class ProductsRepositoryImplTest : MockWebServerBaseTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    override fun isMockServerEnabled(): Boolean = true
    private lateinit var productsRepositoryImpl: ProductsRepositoryImpl
    private lateinit var shoppingApiService: ShoppingApiService

    @Before
    fun setup() {
        shoppingApiService = provideTestApiService()
        productsRepositoryImpl = ProductsRepositoryImpl(shoppingApiService)
    }

    @Test
    fun `given response ok when fetching results then return a list with elements`() {
        runBlocking {
            mockHttpResponse("fetch_products_response.json", HttpURLConnection.HTTP_OK)
            val apiResponse = productsRepositoryImpl.getProducts()

            assertThat(apiResponse).isNotNull()
            assertThat(apiResponse.data).hasSize(8)
        }
    }

    @Test
    fun `given response failure when fetching results then return exception`() {
        runBlocking {
            mockHttpResponse(502)
            val apiResponse = productsRepositoryImpl.getProducts()

            Assert.assertNotNull(apiResponse)
            val expectedValue = Resource.Error(R.string.error_message, null)
            assertThat(expectedValue.message).isEqualTo(apiResponse.message)
        }
    }
}