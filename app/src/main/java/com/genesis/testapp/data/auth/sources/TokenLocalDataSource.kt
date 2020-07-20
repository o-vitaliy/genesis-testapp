package com.genesis.testapp.data.auth.sources

import android.content.SharedPreferences
import com.genesis.testapp.data.prefs.prefString

class TokenLocalDataSource(preferences: SharedPreferences) :
    TokenDataSource {

    private var tokenValue: String? by prefString(preferences,
        AUTH_TOKEN
    )
    override fun getToken(): String? = tokenValue
    override fun setToken(token: String?) {
        tokenValue = token
    }

    companion object {
        const val AUTH_TOKEN = "auth_token"
    }
}
