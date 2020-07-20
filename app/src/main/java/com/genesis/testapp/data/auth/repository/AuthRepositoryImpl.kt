package com.genesis.testapp.data.auth.repository

import com.genesis.testapp.data.auth.sources.AuthDataSource
import com.genesis.testapp.data.auth.sources.TokenDataSource
import com.genesis.testapp.domain.auth.AuthRepository
import com.genesis.testapp.domain.auth.LoginRequest

class AuthRepositoryImpl(
    private val authDataSource: AuthDataSource,
    private val tokenDataSource: TokenDataSource
) : AuthRepository {
    override suspend fun login(request: LoginRequest) {
        val token = authDataSource.login(request.code)
        tokenDataSource.setToken(token)
    }

    override suspend fun isLoggedIn(): Boolean = tokenDataSource.getToken() != null
}
