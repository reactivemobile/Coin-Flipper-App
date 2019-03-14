package com.reactivemobile.app.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class Network {
    object RetrofitFactory {
        private const val baseUrl = "https://jsonplaceholder.typicode.com"

        private val interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        fun makeRetrofitService(): RetrofitService {
            return Retrofit.Builder().baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(RetrofitService::class.java)
        }
    }
}