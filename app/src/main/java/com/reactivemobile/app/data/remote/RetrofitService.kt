package com.reactivemobile.app.data.remote

import com.reactivemobile.app.data.model.Coin
import io.reactivex.Observable
import retrofit2.http.GET

interface RetrofitService {
    @GET("outcomes")
    fun getOutcomes(): Observable<List<Coin>>

    @GET("flip")
    fun flipCoin(): Observable<Coin>
}