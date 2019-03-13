package com.reactivemobile.app.data.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class Network {
    object RetrofitFactory {
        const val baseUrl = "https://jsonplaceholder.typicode.com"

        fun makeRetrofitService(): RetrofitService {
            return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(RetrofitService::class.java)
        }
    }
}