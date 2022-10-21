package com.example.androidcourseex9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var weatherRecyclerView: RecyclerView
    lateinit var weatherRVAdapter: WeatherItemRVAdapter
    private lateinit var buttonGet: Button

    private val weathersList: ArrayList<WeatherItemModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonGet = findViewById(R.id.buttonGet)

        weatherRVAdapter = WeatherItemRVAdapter(weathersList)
        weatherRecyclerView = findViewById(R.id.idRVWeatherItems)
        weatherRecyclerView.adapter = weatherRVAdapter

        buttonGet.setOnClickListener{
            getData()
        }
    }

    fun getData(){

        val cityNames = listOf("Tokyo", "Novosibirsk", "Moscow")

        val queue = Volley.newRequestQueue(this)

        for (name in cityNames){
            val link = "https://yahoo-weather5.p.rapidapi.com/weather?location=$name&format=json&u=c"
            val stringRequest = object: StringRequest(
                Request.Method.GET, link,
                Response.Listener<String> { response ->
                    val content = JSONObject(response)
                    if (content != null ){
                        val temp = content.getJSONObject("current_observation")?.getJSONObject("condition")?.getInt("temperature")
                        if (temp != null){
                            weathersList.add(WeatherItemModel(name, "$temp °C"))
                            weatherRVAdapter.notifyDataSetChanged()
                        }
                    }
                },
                Response.ErrorListener {  })
            {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers["X-RapidAPI-Key"] = "85d73a73c6msh923a91b87ee6eaep104d13jsn57875f488e8a"
                    headers["X-RapidAPI-Host"] = "yahoo-weather5.p.rapidapi.com"
                    return headers
                }
            }
            queue.add(stringRequest)
        }
    }
}