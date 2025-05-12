package com.example.tplogin.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tplogin.R
import com.example.tplogin.User

class MyAdapter(
    private val context: Context, private val userList: List<User>
) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var id: TextView
        var title: TextView

        init {
            id = itemView.findViewById(R.id.tv_id)
            title = itemView.findViewById(R.id.tv_title)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.item_user,
                parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder,position: Int) {
        holder.id.text = userList[position].userId.toString()
        holder.title.text = userList[position].title
    }
}


