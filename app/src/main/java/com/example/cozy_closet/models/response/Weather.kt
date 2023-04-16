package com.example.cozy_closet.models.response

import com.google.gson.annotations.SerializedName

data class Weather(
    val latitude: Double,
    val longitude: Double,
    @SerializedName("generationtime_ms") val generationtimeMs: Double,
    val utc_offset_seconds: Double,
    val timezone: String,
    @SerializedName("timezone_abbreviation") val timezoneAbbreviation: String,
    val elevation: Double,
    @SerializedName("current_weather") val currentWeather: CurrentWeather,
    @SerializedName("hourly_units") val hourlyUnits: HourlyUnits,
    @SerializedName("hourly") val hourly: Hourly
)
//current_weather