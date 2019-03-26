package com.reactivemobile.app.data.remote

import com.reactivemobile.app.data.model.Monarch
import io.reactivex.Observable
import retrofit2.http.GET

interface RetrofitService {
    @GET("api/data?list=englishmonarchs&format=json")
    fun getCountries(): Observable<List<Monarch>>
}