package com.example.wether

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.wether.databinding.ActivityMainBinding
import org.json.JSONObject

const val  API_KEY = "0b034281abd144a1beb170419220906"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var textCity : TextView
    lateinit var textTemp : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        antiPrtSc();
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bGet.setOnClickListener{

            textCity = findViewById<TextView>(R.id.cityText)
            textCity.text = "В Волгограде сейчас"
            getResult("Volgograd")



        }
    }

    private fun getResult(name: String){

        val url = "https://api.weatherapi.com/v1/current.json?key=$API_KEY&q=$name&aqi=no"

        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                response->
                val obj = JSONObject(response)
                val temp = obj.getJSONObject("current")
                Log.d("MyLog", "Response: ${temp.getString("temp_c")}")
                textTemp = findViewById<TextView>(R.id.tempText)
                textTemp.text = "${temp.getString("temp_c")} C"
            },
            {
                Log.d("MyLog", "Volley error: ${it.toString()}")
            }
            )
        queue.add(stringRequest)




    }

    private fun antiPrtSc(){
        getWindow().
        setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        );
    }



}