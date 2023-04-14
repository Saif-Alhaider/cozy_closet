package com.example.cozy_closet.models.response

data class CurrentWeather(
    val temperature: Double,
    val wind_speed: Double,
    val wind_direction: Double,
    val weather_code: Int,
    val is_day: Int,
    val time: String
)
