package com.reactivemobile.app.ui.main

import com.reactivemobile.app.data.model.Post
import com.reactivemobile.app.data.remote.Repository
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainPresenter @Inject constructor(private val repository: Repository) : MainContract.Presenter {

    lateinit var mainView: MainContract.View

    override fun handleButtonClicked(): Disposable? {
        return repository.fetch().subscribe(this::showPosts)
    }

    override fun attach(view: MainContract.View) {
        mainView = view
    }

    override fun viewReady() {
        loadData()
    }

    private fun loadData() {
        if (repository.cachedList.isNotEmpty()) {
            showPosts(repository.cachedList)
        }
    }

    private fun showPosts(result: List<Post>?) {
        if (result != null) {
            mainView.showPosts(result)
        }
    }
}