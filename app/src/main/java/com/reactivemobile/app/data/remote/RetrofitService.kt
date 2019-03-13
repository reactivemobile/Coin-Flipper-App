package com.reactivemobile.app.data.remote

import com.reactivemobile.app.data.model.Post
import io.reactivex.Observable
import retrofit2.http.GET

interface RetrofitService {
    @GET("/posts")
    fun getPosts(): Observable<List<Post>>
}