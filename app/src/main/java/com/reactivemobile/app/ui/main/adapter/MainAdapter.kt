package com.reactivemobile.app.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reactivemobile.app.R
import com.reactivemobile.app.data.model.Coin

class MainAdapter(private val postList: List<Coin>) : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val textView = LayoutInflater.from(parent.context).inflate(R.layout.main_list_item, parent, false)
        return MyViewHolder(textView)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setText(postList[position].value)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun setText(text: String) {
            (itemView as TextView).text = text
        }
    }
}