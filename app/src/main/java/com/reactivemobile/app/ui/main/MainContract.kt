package com.reactivemobile.app.ui.main

import com.reactivemobile.app.ui.base.BaseContract

class MainContract {
    interface View : BaseContract.View {
        fun showCount(count: Int)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun handleButtonClicked()
    }
}