package com.reactivemobile.app.ui.main

import com.reactivemobile.app.data.model.Item
import com.reactivemobile.app.data.remote.Repository
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainPresenter @Inject constructor(private val repository: Repository) : MainContract.Presenter {

    lateinit var mainView: MainContract.View

    override fun handleButtonClicked(): Disposable? {
        return repository.fetch()
            .onErrorReturn { emptyList() }
            .doOnError { showError() }
            .subscribe(this::showCountries)
    }

    private fun showError() {
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
            showCountries(repository.cachedList)
        }
    }

    private fun showCountries(result: List<Item>?) {
        if (result != null) {
            mainView.showCountries(result)
        }
    }
}