package com.reactivemobile.app.ui.main

import com.reactivemobile.app.data.model.Coin
import com.reactivemobile.app.data.remote.Repository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {

    lateinit var mainPresenter: MainPresenter

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var mainView: MainContract.View

    @Before
    fun setUp() {
        `when`(repository.flipCoin()).thenReturn(Single.just(Coin("heads")))
        `when`(repository.fetchOutcomes()).thenReturn(Single.just(ArrayList()))

        mainPresenter = MainPresenter(repository)
        mainPresenter.attach(mainView)
    }

    @Test
    fun fetchResults() {
        mainPresenter.fetchResults()

        verify(repository).fetchOutcomes()
    }

    @Test
    fun flipCoin_withSuccessfulFlip_updatesUi() {
        mainPresenter.flipCoin()

        verify(repository).flipCoin()
    }
}