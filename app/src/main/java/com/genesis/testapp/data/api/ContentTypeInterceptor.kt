package com.genesis.testapp.data.api

import okhttp3.Interceptor
import okhttp3.Response

class ContentTypeInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("Content-Type", "application/json")
        return chain.proceed(builder.build())
    }
}
