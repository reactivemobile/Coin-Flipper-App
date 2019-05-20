package com.reactivemobile.app.injection.module

import com.reactivemobile.app.data.model.Coin
import com.reactivemobile.app.data.remote.NetworkService
import dagger.Module
import dagger.Provides
import io.reactivex.Single

@Module
class NetworkModule {

    @Provides
    fun provideFakeRetrofit(): NetworkService {
        return object : NetworkService {
            override fun flipCoin(): Single<Coin> {
                return Single.just(Coin("tails"))
            }

            override fun getOutcomes(): Single<List<Coin>> {
                return Single.just(listOf(Coin("heads")))
            }
        }
    }
}