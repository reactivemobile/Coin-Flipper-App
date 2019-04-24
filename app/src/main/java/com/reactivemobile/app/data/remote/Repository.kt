package com.reactivemobile.app.data.remote

import com.reactivemobile.app.data.model.Coin
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class Repository {
    private val service = Network.RetrofitFactory.makeRetrofitService()
    var cachedList: List<Coin> = emptyList()

    fun fetchOutcomes(): Observable<List<Coin>> {
        return service.getOutcomes()
            .subscribeOn(Schedulers.io())
            .doAfterNext { list -> cacheList(list) }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun flipCoin(): Observable<Coin> {
        return service.flipCoin()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun cacheList(list: List<Coin>?) {
        list.let {
            cachedList = list!!
        }
    }
}
