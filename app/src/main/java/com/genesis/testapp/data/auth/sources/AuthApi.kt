package com.genesis.testapp.data.auth.sources

import com.genesis.testapp.data.auth.responses.TokenResponse
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {

    @Headers("Accept: application/json")
    @POST("oauth/access_token")
    suspend fun getProfile(
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("code") code: String
    ): TokenResponse
}
