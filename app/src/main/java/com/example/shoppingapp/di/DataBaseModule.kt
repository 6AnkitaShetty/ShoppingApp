package com.example.shoppingapp.di

import android.app.Application
import androidx.room.Room
import com.example.shoppingapp.features.home.data.local.CartDao
import com.example.shoppingapp.features.home.data.local.ShoppingDatabase
import com.example.shoppingapp.features.home.data.local.WishListDao
import com.example.shoppingapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun provideShoppingDatabase(app: Application): ShoppingDatabase {
        return Room.databaseBuilder(
            app,
            ShoppingDatabase::class.java,
            Constants.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideCartDao(shoppingDatabase: ShoppingDatabase): CartDao {
        return shoppingDatabase.cartDao
    }

    @Singleton
    @Provides
    fun provideWishListDao(shoppingDatabase: ShoppingDatabase): WishListDao {
        return shoppingDatabase.wishlistDao
    }
}