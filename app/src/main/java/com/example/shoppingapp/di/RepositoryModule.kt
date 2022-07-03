package com.example.shoppingapp.di

import android.content.Context
import com.example.shoppingapp.features.home.data.local.ShoppingDatabase
import com.example.shoppingapp.features.home.data.remote.ShoppingApiService
import com.example.shoppingapp.features.home.data.repository.CartRepositoryImpl
import com.example.shoppingapp.features.home.data.repository.ProductsRepositoryImpl
import com.example.shoppingapp.features.home.data.repository.WishListRepositoryImpl
import com.example.shoppingapp.features.home.domain.repository.CartRepository
import com.example.shoppingapp.features.home.domain.repository.ProductsRepository
import com.example.shoppingapp.features.home.domain.repository.WishListRepository
import com.example.shoppingapp.features.login.data.repository.DataStoreRepositoryImpl
import com.example.shoppingapp.features.login.data.repository.LoginRepositoryImpl
import com.example.shoppingapp.features.login.domain.repository.DataStoreRepository
import com.example.shoppingapp.features.login.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideLoginRepository(): LoginRepository {
        return LoginRepositoryImpl()
    }

    @Singleton
    @Provides
    fun provideDataStoreRepository(
        @ApplicationContext app: Context
    ): DataStoreRepository = DataStoreRepositoryImpl(app)

    @Provides
    fun provideCartRepository(db: ShoppingDatabase): CartRepository {
        return CartRepositoryImpl(db.cartDao)
    }

    @Provides
    fun provideWishListRepository(db: ShoppingDatabase): WishListRepository {
        return WishListRepositoryImpl(db.wishlistDao)
    }

    @Provides
    fun provideProductRepository(shoppingApiService: ShoppingApiService): ProductsRepository {
        return ProductsRepositoryImpl(shoppingApiService)
    }
}