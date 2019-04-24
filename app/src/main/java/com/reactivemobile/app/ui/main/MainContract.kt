package com.reactivemobile.app.ui.main

import com.reactivemobile.app.data.model.Item
import com.reactivemobile.app.ui.base.BaseContract
import io.reactivex.disposables.Disposable

class MainContract {
    interface View : BaseContract.View {
        fun showResult(responseItems: List<Item>)
        fun showError()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun handleButtonClicked(): Disposable?
    }
}