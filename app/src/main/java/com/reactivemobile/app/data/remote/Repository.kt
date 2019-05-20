package com.reactivemobile.app.data.remote

import com.reactivemobile.app.data.model.Coin
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Repository @Inject constructor(private val service: NetworkService) {

    var cachedList: MutableList<Coin> = mutableListOf()

    fun fetchOutcomes(): Single<List<Coin>> {
        return service.getOutcomes()
            .subscribeOn(Schedulers.io())
            .doAfterSuccess { list -> cacheList(list) }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun flipCoin(): Single<Coin> {
        return service.flipCoin()
            .doAfterSuccess { coin -> cachedList.add(coin) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun cacheList(list: List<Coin>?) {
        list.let {
            cachedList = ArrayList(list!!)
        }
    }
}
