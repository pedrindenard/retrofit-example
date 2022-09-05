package com.app.myapplication.feature.data.remote

import com.app.myapplication.BuildConfig
import com.app.myapplication.feature.data.remote.interceptor.ApiKey
import com.app.myapplication.feature.data.remote.network.Api
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Retrofit {

    companion object {

        fun create(): Api {
            val client = OkHttpClient.Builder()
                .connectTimeout(timeout = 30, TimeUnit.SECONDS)
                .readTimeout(timeout = 30, TimeUnit.SECONDS)
                .addInterceptor(ApiKey())
                .build()

            return Retrofit.Builder()
                .baseUrl(BuildConfig.API_TMDB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(client)
                .build()
                .create(Api::class.java)
        }
    }
}