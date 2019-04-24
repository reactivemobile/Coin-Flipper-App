package com.reactivemobile.app.ui.main

import com.reactivemobile.app.data.model.Coin
import com.reactivemobile.app.data.remote.Repository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainPresenter @Inject constructor(private val repository: Repository) : MainContract.Presenter {

    private lateinit var mainView: MainContract.View

    private var compositeDisposable = CompositeDisposable()

    override fun fetchResults() {
        compositeDisposable.add(
            repository
                .fetchOutcomes()
                .subscribe(
                    this::showOutcomes,
                    this::showError,
                    this::hideLoading,
                    this::showLoading
                )
        )
    }

    override fun flipCoin() {
        compositeDisposable.add(
            repository
                .flipCoin()
                .subscribe(
                    this::showFlipCoinResult,
                    this::showError,
                    this::hideLoading,
                    this::showLoading
                )
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
            showOutcomes(repository.cachedList)
        }
    }

    private fun showOutcomes(result: List<Coin>) {
        mainView.showOutcomes(result)
    }

    private fun showFlipCoinResult(result: Coin) {
        mainView.showCoinFlipResult(result)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}