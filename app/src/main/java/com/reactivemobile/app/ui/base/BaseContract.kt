package com.reactivemobile.app.ui.base

class BaseContract {

    interface Presenter<in T> {
        fun attach(view: T)
        fun viewReady()
        fun onDestroy()
    }

    interface View {
        fun showLoading()
        fun hideLoading()
    }
}