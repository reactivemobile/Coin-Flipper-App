package com.reactivemobile.app.data.remote

import com.reactivemobile.app.data.model.Monarch
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class Repository {
    private val service = Network.RetrofitFactory.makeRetrofitService()
    var cachedList: List<Monarch> = emptyList()

    fun fetch(): Observable<List<Monarch>> {

        return service.getCountries()
            .subscribeOn(Schedulers.io())
            .doAfterNext { list -> cacheList(list) }
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun cacheList(list: List<Monarch>?) {
        list.let {
            cachedList = list!!
        }
    }
}
