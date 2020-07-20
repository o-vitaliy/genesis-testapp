package com.genesis.testapp.domain.auth

interface AuthRepository {
    suspend fun login(request: LoginRequest)
    suspend fun isLoggedIn(): Boolean
}
