package com.reactivemobile.app.data.remote

import com.reactivemobile.app.data.model.Coin
import io.reactivex.Single
import retrofit2.http.GET

interface NetworkService {
    @GET("outcomes")
    fun getOutcomes(): Single<List<Coin>>

    @GET("flip")
    fun flipCoin(): Single<Coin>
}