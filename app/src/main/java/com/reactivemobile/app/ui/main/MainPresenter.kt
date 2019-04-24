package com.reactivemobile.app.ui.main

import com.reactivemobile.app.data.model.Item
import com.reactivemobile.app.data.remote.Repository
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainPresenter @Inject constructor(private val repository: Repository) : MainContract.Presenter {

    private lateinit var mainView: MainContract.View

    override fun handleButtonClicked(): Disposable? {
        return repository
            .fetch()
            .subscribe(
                this::showResult,
                this::showError,
                this::hideLoading,
                this::showLoading
            )
    }

    private fun showLoading(observable: Any) {
        mainView.showLoading()
    }

    private fun hideLoading() {
        mainView.hideLoading()
    }

    private fun showError(throwable: Throwable?) {
        hideLoading()
        mainView.showError()
    }

    override fun attach(view: MainContract.View) {
        mainView = view
    }

    override fun viewReady() {
        loadData()
    }

    private fun loadData() {
        if (repository.cachedList.isNotEmpty()) {
            showResult(repository.cachedList)
        }
    }

    private fun showResult(result: List<Item>) {
        mainView.showResult(result)
    }
}