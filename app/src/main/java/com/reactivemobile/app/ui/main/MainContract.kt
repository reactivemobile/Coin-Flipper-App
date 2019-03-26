package com.reactivemobile.app.ui.main

import com.reactivemobile.app.data.model.Monarch
import com.reactivemobile.app.ui.base.BaseContract
import io.reactivex.disposables.Disposable

class MainContract {
    interface View : BaseContract.View {
        fun showCountries(responseItems: List<Monarch>)
        fun showError()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun handleButtonClicked(): Disposable?
    }
}