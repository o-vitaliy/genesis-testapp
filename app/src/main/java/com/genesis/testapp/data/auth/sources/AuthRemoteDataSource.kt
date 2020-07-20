package com.genesis.testapp.data.auth.sources

import com.genesis.testapp.R
import com.genesis.testapp.data.ResourceProvider
import com.genesis.testapp.data.auth.sources.AuthApi
import com.genesis.testapp.data.auth.sources.AuthDataSource

class AuthRemoteDataSource(
    private val api: AuthApi,
    private val resourceProvider: ResourceProvider
) : AuthDataSource {
    override suspend fun login(code: String): String {
        val token = api.getProfile(
            resourceProvider.getString(R.string.github_client_id),
            resourceProvider.getString(R.string.github_client_secret),
            code
        ).token
        return checkNotNull(token)
    }

}