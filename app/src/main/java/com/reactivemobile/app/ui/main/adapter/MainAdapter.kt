package com.reactivemobile.app.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reactivemobile.app.R
import com.reactivemobile.app.data.model.Post

class MainAdapter(private val postList: List<Post>) : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_list_item, parent, false) as TextView
        return MyViewHolder(textView)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setText(postList.get(position).title)
    }

    class MyViewHolder(view: TextView) : RecyclerView.ViewHolder(view) {
        fun setText(text: String) {
            (itemView as TextView).text = text
        }
    }
}