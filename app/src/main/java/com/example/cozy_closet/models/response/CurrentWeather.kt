package com.example.cozy_closet.models.response

import com.google.gson.annotations.SerializedName

data class CurrentWeather(
    @SerializedName("temperature") val temperature: Double,
    @SerializedName("wind_speed") val windSpeed: Double,
    @SerializedName("wind_direction") val windDirection: Double,
    @SerializedName("weather_code") val weatherCode: Int,
    @SerializedName("is_day") val isDay: Int,
    @SerializedName("time") val time: String
)
