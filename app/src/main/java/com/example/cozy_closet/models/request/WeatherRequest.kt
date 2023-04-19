package com.example.cozy_closet.models.request

import com.google.gson.annotations.SerializedName

data class WeatherRequest(
    @SerializedName("latitude") val latitude:Double,
    @SerializedName("longitude") val longitude:Double,
    @SerializedName("hourly") val hourly:String,
    @SerializedName("current_weather") val currentWeather:Boolean
)
