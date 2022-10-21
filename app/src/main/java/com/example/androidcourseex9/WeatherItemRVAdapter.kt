package com.example.androidcourseex9

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class WeatherItemRVAdapter (
    private var weathersList: ArrayList<WeatherItemModel>,
): RecyclerView.Adapter<WeatherItemRVAdapter.WeatherViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherItemRVAdapter.WeatherViewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.weather_rv_item,
            parent, false
        )

        return WeatherViewHolder(itemView)

    }

    fun filterList(filterList: ArrayList<WeatherItemModel>){
        weathersList = filterList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: WeatherItemRVAdapter.WeatherViewHolder, position: Int) {
        val w = weathersList[position]
        if (w != null){
            holder.cityNameTextView.text = w.cityName
            holder.temperatureTextView.text = w.temp
        }
    }

    override fun getItemCount(): Int {
        return weathersList.size
    }

    class WeatherViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val cityNameTextView: TextView = itemView.findViewById(R.id.idTVCity)
        val temperatureTextView: TextView = itemView.findViewById(R.id.idTVTemp)
    }
}
