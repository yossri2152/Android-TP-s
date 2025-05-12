package com.example.tp6.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp6.R

class CityAdaptor (private val cityList: Array<String>, private val listener:OnCityClickListener):RecyclerView.Adapter<CityAdaptor.CityViewHolder>() {

    class CityViewHolder(private val view: View):RecyclerView.ViewHolder(view){
        val textView:TextView=view.findViewById(R.id.city)
        fun bind(city: String,listener:OnCityClickListener){
            textView.setOnClickListener{
                listener.OnCityClick(city)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
       val adaptorLayout=LayoutInflater.from(parent.context).inflate(R.layout.city_item,parent,false)
        return CityViewHolder(adaptorLayout)
    }

    override fun getItemCount(): Int {
        return cityList.size    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.textView.text=cityList[position]
        holder.bind(cityList[position],listener)
    }
    interface OnCityClickListener {
        fun OnCityClick(city:String)
    }
}