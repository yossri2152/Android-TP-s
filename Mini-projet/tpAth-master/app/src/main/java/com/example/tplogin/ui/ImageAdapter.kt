package com.example.tplogin.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tplogin.R
import com.example.tplogin.User
import com.example.tplogin.data.ImageType

class ImageAdapter(
    private val context: Context, private val imageList: List<ImageType>
) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {



    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView

        init {
            imageView = itemView.findViewById(R.id.imageView)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.item_image,
                parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ViewHolder,position: Int) {

        Glide.with(context).load(imageList[position].urls.regular).into(holder.imageView);


    }
}


