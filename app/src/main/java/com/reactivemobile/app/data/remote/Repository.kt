package com.reactivemobile.app.data.remote

import com.reactivemobile.app.data.model.Post
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class Repository {

    private val service = Network.RetrofitFactory.makeRetrofitService()
    var cachedList: List<Post> = ArrayList()

    fun fetch(): Observable<List<Post>> {

        return service.getPosts()
            .subscribeOn(Schedulers.io())
            .doAfterNext { list -> cacheList(list) }
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun cacheList(list: List<Post>?) {
        list.let {
            cachedList = list!!
        }
    }
}
