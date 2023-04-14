package com.example.cozy_closet.models.request

data class WeatherRequest(
    val latitude:Double,
    val longitude:Double,
    val hourly:String,
    val current_weather:Boolean
)
