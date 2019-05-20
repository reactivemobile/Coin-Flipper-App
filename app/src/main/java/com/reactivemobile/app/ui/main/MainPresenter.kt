package com.reactivemobile.app.ui.main

import com.reactivemobile.app.data.model.Coin
import com.reactivemobile.app.data.remote.Repository
import io.reactivex.disposables.CompositeDisposable

class MainPresenter(private val repository: Repository) : MainContract.Presenter {

    private lateinit var mainView: MainContract.View

    private var compositeDisposable = CompositeDisposable()

    override fun fetchResults() {
        compositeDisposable.add(
            handleLoading(mainView, repository.fetchOutcomes())
                .subscribe(this::showOutcomes, this::showError)
        )
    }

    override fun flipCoin() {
        compositeDisposable.add(
            handleLoading(mainView, repository.flipCoin())
                .subscribe(this::showFlipCoinResult, this::showError)
        )
    }

    private fun showError(throwable: Throwable?) {
        mainView.hideLoading();
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
            showOutcomes(repository.cachedList)
        }
    }

    private fun showOutcomes(result: List<Coin>) {
        mainView.showOutcomes(result)
    }

    private fun showFlipCoinResult(result: Coin) {
        mainView.showCoinFlipResult(result)
        loadData()
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}