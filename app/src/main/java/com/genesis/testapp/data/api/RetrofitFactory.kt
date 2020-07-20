package com.genesis.testapp.data.api

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

object RetrofitFactory {
    private fun createLogging(): HttpLoggingInterceptor =
        HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.tag("OkHttp").d(message)
            }
        }).apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

    private fun createClient(interceptors: Set<Interceptor>): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        with(builder) {
            addInterceptor(createLogging())
            interceptors.forEach {
                addInterceptor(it)
            }
        }
        return builder.build()
    }

    fun create(gson: Gson, apiUrl: String, interceptors: Set<Interceptor>): Retrofit =
        Retrofit.Builder()
            .client(createClient(interceptors))
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
}
