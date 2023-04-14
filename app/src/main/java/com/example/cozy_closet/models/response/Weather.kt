package com.example.cozy_closet.models.response

data class Weather(
    val latitude: Double,
    val longitude: Double,
    val generationtime_ms: Double,
    val utc_offset_seconds: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val elevation: Double,
    val currentWeather: CurrentWeather,
    val hourlyUnits: HourlyUnits,
    val hourly: Hourly
)
