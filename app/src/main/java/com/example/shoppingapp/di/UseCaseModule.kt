package com.example.shoppingapp.di

import com.example.shoppingapp.features.home.domain.repository.CartRepository
import com.example.shoppingapp.features.home.domain.repository.ProductsRepository
import com.example.shoppingapp.features.home.domain.repository.WishListRepository
import com.example.shoppingapp.features.home.domain.usecase.*
import com.example.shoppingapp.features.login.domain.repository.LoginRepository
import com.example.shoppingapp.features.login.domain.usecase.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideLoginUseCases(
        loginRepository: LoginRepository
    ): LoginUseCase {
        return LoginUseCase(
            loginRepository
        )
    }

    @Provides
    fun provideCartUseCases(cartRepository: CartRepository): CartUseCases {
        return CartUseCases(
            getCartItemsUseCase = GetCartItemsUseCase(cartRepository),
            getCartItemByIdUseCase = GetCartItemByIdUseCase(cartRepository),
            addItemToCartUseCase = AddItemToCartUseCase(cartRepository),
            deleteCartItemUseCase = DeleteCartItemUseCase(cartRepository),
            cartItemCountUseCase = GetCartItemCountUseCase(cartRepository),
            cartPriceUseCase = GetTotalCartPriceUseCase(cartRepository),
            addWishListItemToCartUseCase = AddWishListItemToCartUseCase(cartRepository),
            deleteAllItemsInCartUseCase = DeleteAllItemsInCartUseCase(cartRepository)
        )
    }

    @Provides
    fun provideWishListUseCases(wishListRepository: WishListRepository): WishListUseCases {
        return WishListUseCases(
            getWishListItemsUseCase = GetWishListItemsUseCase(wishListRepository),
            getWishListItemCountUseCase = GetWishListItemCountUseCase(wishListRepository),
            addItemToWishListUseCase = AddItemToWishListUseCase(wishListRepository),
            getWishListItemByIdUseCase = GetWishListItemByIdUseCase(wishListRepository),
            deleteWishListItemUseCase = DeleteWishListItemUseCase(wishListRepository)
        )
    }

    @Provides
    fun provideFetchProductsUseCase(
        productsRepository: ProductsRepository,
        wishListUseCases: WishListUseCases
    ): FetchProductsUseCase {
        return FetchProductsUseCase(
                productsRepository,
                wishListUseCases
            )
    }
}