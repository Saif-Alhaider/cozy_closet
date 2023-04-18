package com.example.cozy_closet

import android.util.Log
import com.example.cozy_closet.models.ClothSuggester
import com.example.cozy_closet.models.Clothes
import com.example.cozy_closet.models.WeatherCodes
import com.example.cozy_closet.models.network.WeatherService
import com.example.cozy_closet.models.request.WeatherRequest
import com.example.cozy_closet.models.response.Weather
import com.example.cozy_closet.util.PrefUtil
import com.example.cozy_closet.util.toLocalDateTime
import com.google.gson.Gson
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class Presenter(private val mainActivityView: MainActivityView) {
    fun getData() {
        WeatherService().getWeather(
            WeatherRequest(
                latitude = 47.560539,
                longitude = -52.712830,
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
                checkClothData(weather.currentWeather.temperature)
            },
            onFailure = { mainActivityView.showNoNetworkConnection() }
        )
    }


    private fun convertDateFormat(dateTime: String): String {
        val formatterInput = DateTimeFormatter.ISO_DATE_TIME
        val formatterOutput = DateTimeFormatter.ofPattern("EEE, dd MMMM yyyy", Locale.ENGLISH)
        val parsedDateTime = LocalDateTime.parse(dateTime, formatterInput)
        return formatterOutput.format(parsedDateTime)
    }

    private fun checkClothData(currentTemperature: Double) {
        val clothes = getClothesData().first
        val createdTime = getClothesData().second

        if (createdTime == null) {
            setClothesData(currentTemperature, null)
        }else if (!isSameDayMonthYear(getClothesData().second!!.toLocalDateTime()!!, LocalDateTime.now())){
            setClothesData(currentTemperature, null)
        }
        mainActivityView.showClothes(getClothesData().first!!)
    }

    private fun setClothesData(currentTemperature: Double, currentClothes: Clothes?) {
        PrefUtil.storeClothes(
            ClothSuggester().suggestClothes(currentTemperature, currentClothes)
        )
    }

    private fun getClothesData(): Pair<Clothes?, String?> = PrefUtil.getStoredClothes()

    private fun isSameDayMonthYear(dateTime1: LocalDateTime, dateTime2: LocalDateTime): Boolean {
        return dateTime1.dayOfMonth == dateTime2.dayOfMonth &&
                dateTime1.monthValue == dateTime2.monthValue &&
                dateTime1.year == dateTime2.year
    }

}