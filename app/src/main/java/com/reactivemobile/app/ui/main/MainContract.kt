package com.reactivemobile.app.ui.main

import com.reactivemobile.app.data.model.Post
import com.reactivemobile.app.ui.base.BaseContract

class MainContract {
    interface View : BaseContract.View {
        fun showPosts(posts: List<Post>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun handleButtonClicked()
    }
}