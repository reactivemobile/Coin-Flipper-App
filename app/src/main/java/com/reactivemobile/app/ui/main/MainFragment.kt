package com.reactivemobile.app.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.reactivemobile.app.App
import com.reactivemobile.app.R
import javax.inject.Inject

class MainFragment : Fragment(), MainContract.View {
    @Inject
    lateinit var mainPresenter: MainContract.Presenter

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = activity?.application
        if (application is App) {
            application.appComponent.inject(this)
        }
    }

    companion object {
        fun newInstance() = MainFragment()
        const val TAG = "MainFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainPresenter.attach(this)
        view.findViewById<Button>(R.id.button).setOnClickListener {
            mainPresenter.handleButtonClicked()
        }

        textView = view.findViewById(R.id.text_view)
    }

    override fun showCount(count: Int) {
        textView.text = "count is $count"
    }
}
