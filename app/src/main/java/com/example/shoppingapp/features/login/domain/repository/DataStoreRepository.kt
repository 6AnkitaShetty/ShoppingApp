package com.example.shoppingapp.features.login.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun putString(key: String, value: String)
    suspend fun putBoolean(key: String, value: Boolean)
    suspend fun getString(key: String): Flow<String>?
    suspend fun getBoolean(key: String): Boolean?
}