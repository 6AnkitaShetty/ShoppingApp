package com.example.shoppingapp.features.login.repository

import com.example.shoppingapp.features.login.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class FakeDataStoreRepository : DataStoreRepository {
    override suspend fun putString(key: String, value: String) {
        TODO("Not yet implemented")
    }

    override suspend fun putBoolean(key: String, value: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun getString(key: String): Flow<String>? {
        TODO("Not yet implemented")
    }

    override suspend fun getBoolean(key: String): Boolean? {
        TODO("Not yet implemented")
    }
}