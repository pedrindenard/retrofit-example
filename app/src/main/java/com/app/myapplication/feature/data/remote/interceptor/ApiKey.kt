package com.app.myapplication.feature.data.remote.interceptor

import com.app.myapplication.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKey : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val parameters = chain.request().url.newBuilder()
            .addQueryParameter(name = "api_key", value = BuildConfig.API_TMDB_KEY)
            .addQueryParameter(name = "language", value = BuildConfig.API_TMDB_LANGUAGE)
            .build()

        val newBuilder = chain.request().newBuilder().url(parameters)

        return chain.proceed(newBuilder.build())
    }
}