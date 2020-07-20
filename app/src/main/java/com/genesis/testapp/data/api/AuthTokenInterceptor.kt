package com.genesis.testapp.data.api

import com.genesis.testapp.data.auth.sources.TokenDataSource
import okhttp3.Interceptor
import okhttp3.Response

class AuthTokenInterceptor(
    private val authTokenDataSource: TokenDataSource
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        authTokenDataSource.getToken()?.let {
            builder.addHeader(AUTH_HEADER, "token $it")
        }
        return chain.proceed(builder.build())
    }

    companion object {
        private const val AUTH_HEADER = "Authorization"
    }
}
