package com.example.cozy_closet.models

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
    }
}
