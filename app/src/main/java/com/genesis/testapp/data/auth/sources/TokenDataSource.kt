package com.genesis.testapp.data.auth.sources

interface TokenDataSource {
    fun getToken(): String?
    fun setToken(token: String?)
}