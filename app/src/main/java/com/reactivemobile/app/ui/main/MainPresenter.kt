package com.reactivemobile.app.ui.main

import com.reactivemobile.app.data.remote.Repository
import com.reactivemobile.app.data.model.Post
import javax.inject.Inject

class MainPresenter @Inject constructor(private val repository: Repository) : MainContract.Presenter {

    lateinit var mainView: MainContract.View

    override fun handleButtonClicked() {
        repository.fetch().subscribe(this::showCount)
    }

    override fun attach(view: MainContract.View) {
        mainView = view
    }

    private fun showCount(result: List<Post>?) {
        if (result != null) {
            mainView.showCount(result.size)
        }
    }
}