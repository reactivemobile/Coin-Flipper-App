package com.reactivemobile.app.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reactivemobile.app.App
import com.reactivemobile.app.R
import com.reactivemobile.app.data.model.Post
import com.reactivemobile.app.ui.main.adapter.MainAdapter
import javax.inject.Inject

class MainFragment : Fragment(), MainContract.View {
    @Inject
    lateinit var mainPresenter: MainContract.Presenter

    private lateinit var textView: TextView

    private lateinit var recyclerView: RecyclerView

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
        recyclerView = view.findViewById(R.id.recycler_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        mainPresenter.viewReady()
    }

    override fun showPosts(posts: List<Post>) {
        recyclerView.adapter = MainAdapter(posts)
    }
}
