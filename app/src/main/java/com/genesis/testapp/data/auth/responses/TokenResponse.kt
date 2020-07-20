package com.genesis.testapp.data.auth.responses

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("access_token") val token: String?
)
