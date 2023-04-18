package com.example.cozy_closet.models

import com.example.cozy_closet.R

enum class WeatherCodes(val description: String, val codes: List<Int>) {
    CLEAR_SKY("Clear sky", listOf(0)),
    PARTLY_CLOUDY("Partly cloudy", listOf(1, 2, 3)),
    FOG("Fog", listOf(45, 48)),
    FREEZING_RAIN("Freezing Rain", listOf(66, 67)),
    SNOW_FALL("Snow fall", listOf(71, 73, 75, 85, 86, 77, 51, 53, 55)),
    RAIN("Rain", listOf(80, 81, 82, 56, 57, 61, 63, 65)),
    THUNDERSTORM("Thunderstorm", listOf(95, 96, 99));

    companion object {
        fun getCodeDescription(code: Int): String? {
            return values().find { it.codes.contains(code) }?.description
        }

        fun getWeatherIconFromWeatherCode(weatherCode: Int): Int {
            return when (WeatherCodes.values().find { it.codes.contains(weatherCode) }) {
                WeatherCodes.CLEAR_SKY -> R.drawable.clear
                WeatherCodes.PARTLY_CLOUDY -> R.drawable.cloudy
                WeatherCodes.FOG -> R.drawable.fog
                WeatherCodes.FREEZING_RAIN -> R.drawable.chance_of_freezing_rain
                WeatherCodes.SNOW_FALL -> R.drawable.snow
                WeatherCodes.RAIN -> R.drawable.rain
                else -> R.drawable.thunderstorm
            }
        }
    }
}
