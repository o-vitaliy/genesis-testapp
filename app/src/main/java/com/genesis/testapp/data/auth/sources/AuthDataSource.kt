package com.genesis.testapp.data.auth.sources

interface AuthDataSource {
    suspend fun login(code: String): String
}
