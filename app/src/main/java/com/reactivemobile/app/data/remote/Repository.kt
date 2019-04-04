package com.reactivemobile.app.data.remote

import com.reactivemobile.app.data.model.Item
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class Repository {
    private val service = Network.RetrofitFactory.makeRetrofitService()
    var cachedList: List<Item> = emptyList()

    fun fetch(): Observable<List<Item>> {

        return service.get()
            .subscribeOn(Schedulers.io())
            .doAfterNext { list -> cacheList(list) }
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun cacheList(list: List<Item>?) {
        list.let {
            cachedList = list!!
        }
    }
}
