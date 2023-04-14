package com.example.cozy_closet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cozy_closet.models.network.WeatherService
import com.example.cozy_closet.models.request.WeatherRequest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WeatherService().getWeather(
            WeatherRequest(
                latitude = 33.23,
                longitude = 44.33,
                hourly = "temperature_80m",
                current_weather = true
            )
        )
    }
}