package com.reactivemobile.app.data.remote

import com.reactivemobile.app.data.model.Item
import io.reactivex.Observable
import retrofit2.http.GET

interface RetrofitService {
    @GET("outcomes")
    fun get(): Observable<List<Item>>
}