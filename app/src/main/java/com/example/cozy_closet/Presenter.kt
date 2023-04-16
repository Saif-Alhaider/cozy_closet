package com.example.cozy_closet

import android.util.Log
import com.example.cozy_closet.models.WeatherCodes
import com.example.cozy_closet.models.network.WeatherService
import com.example.cozy_closet.models.request.WeatherRequest
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class Presenter(private val mainActivityView: MainActivityView) {
    private fun setWeatherCardData() {
        WeatherService().getWeather(
            WeatherRequest(
                latitude = 33.23,
                longitude = 44.33,
                hourly = "temperature_80m",
                current_weather = true
            ),
            onSuccess = mainActivityView::onSuccess,
            onFailure = mainActivityView::onFailure
        )
    }




}