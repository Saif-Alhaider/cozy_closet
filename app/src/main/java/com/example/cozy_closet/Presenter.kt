package com.example.cozy_closet

import com.example.cozy_closet.models.ClothSuggester
import com.example.cozy_closet.models.Clothes
import com.example.cozy_closet.models.WeatherCodes
import com.example.cozy_closet.models.network.WeatherService
import com.example.cozy_closet.models.request.WeatherRequest
import com.example.cozy_closet.models.response.Weather
import com.example.cozy_closet.util.PrefUtil
import com.google.gson.Gson
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class Presenter(private val mainActivityView: MainActivityView) {
    fun getWeatherData() {
        WeatherService().getWeather(
            WeatherRequest(
                latitude = 33.23,
                longitude = 44.33,
                hourly = "temperature_80m",
                current_weather = true
            ),
            onSuccess = { response ->

                val weather =
                    Gson().fromJson(response.body?.string().toString(), Weather::class.java)
                val currentTime = LocalDateTime.now().toString()
                val date = convertDateFormat(currentTime)
                val weatherDescription =
                    WeatherCodes.getCodeDescription(weather.currentWeather.weatherCode)

                mainActivityView.showWeatherData(
                    weather,
                    date,
                    weatherDescription ?: "Weather Description"
                )
            },
            onFailure = { mainActivityView.showNoNetworkConnection() }
        )
    }

    private fun setClothesData(currentTemperature: Double, currentClothes: Clothes?) {
        PrefUtil.storeClothes(
            ClothSuggester().suggestClothes(currentTemperature, currentClothes)
        )
    }

    private fun getClothesData(): Pair<Clothes, String?> = PrefUtil.getStoredClothes()

    private fun convertDateFormat(dateTime: String): String {
        val formatterInput = DateTimeFormatter.ISO_DATE_TIME
        val formatterOutput = DateTimeFormatter.ofPattern("EEE, dd MMMM yyyy", Locale.ENGLISH)
        val parsedDateTime = LocalDateTime.parse(dateTime, formatterInput)
        return formatterOutput.format(parsedDateTime)
    }

}