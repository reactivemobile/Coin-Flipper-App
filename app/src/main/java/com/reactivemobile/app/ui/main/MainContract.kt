package com.reactivemobile.app.ui.main

import com.reactivemobile.app.data.model.Coin
import com.reactivemobile.app.ui.base.BaseContract

class MainContract {
    interface View : BaseContract.View {
        fun showOutcomes(responseCoins: List<Coin>)
        fun showCoinFlipResult(result: Coin)
        fun showError()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun fetchResults()
        fun flipCoin()
    }
}