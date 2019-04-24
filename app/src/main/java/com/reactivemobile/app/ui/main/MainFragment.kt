package com.reactivemobile.app.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.reactivemobile.app.App
import com.reactivemobile.app.R
import com.reactivemobile.app.data.model.Coin
import com.reactivemobile.app.ui.main.adapter.MainAdapter
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

class MainFragment : Fragment(), MainContract.View {
    @Inject
    lateinit var mainPresenter: MainContract.Presenter

    companion object {
        fun newInstance() = MainFragment()
        const val TAG = "MainFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as App).appComponent.inject(this)
        mainPresenter.attach(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        mainPresenter.viewReady()
    }

    private fun setupView() {
        fetch_outcomes.setOnClickListener {
            mainPresenter.fetchResults()
        }

        flip_coin.setOnClickListener {
            mainPresenter.flipCoin()
        }

        recycler_view.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    override fun showOutcomes(responseCoins: List<Coin>) {
        recycler_view.adapter = MainAdapter(responseCoins)
    }

    override fun showCoinFlipResult(result: Coin) {
        Toast.makeText(context, "Outcome was ${result.face}", Toast.LENGTH_SHORT).show()
    }

    override fun showError() {
        Toast.makeText(context, "Error loading data", Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        loading_view.visibility = VISIBLE
    }

    override fun hideLoading() {
        loading_view.visibility = GONE
    }
}
