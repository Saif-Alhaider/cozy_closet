package com.example.cozy_closet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cozy_closet.databinding.ActivityMainBinding
import com.example.cozy_closet.databinding.ItemWeatherCardBinding
import com.example.cozy_closet.models.WeatherCodes
import com.example.cozy_closet.models.network.WeatherService
import com.example.cozy_closet.models.request.WeatherRequest
import com.example.cozy_closet.models.response.Weather
import com.google.gson.Gson
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setWeatherCardData()
    }

    private fun setWeatherCardData() {
        WeatherService().getWeather(
            WeatherRequest(
                latitude = 33.23,
                longitude = 44.33,
                hourly = "temperature_80m",
                current_weather = true
            ),
            onSuccess = {
                val result = Gson().fromJson(it.body?.string().toString(), Weather::class.java)
                runOnUiThread {
                    val weatherCode = result.currentWeather.weather_code
                    val weatherType =
                        WeatherCodes.getCodeDescription(weatherCode)
                    val date = convertDateFormat(result.currentWeather.time)
                    val temperature = result.currentWeather.temperature
                    binding.run {
                        weatherCard.textViewWeatherType.text = weatherType
                        weatherCard.textViewDate.text = date
                        weatherCard.textViewWeatherTemp.text =
                            getString(R.string.temperature, temperature.toString())
                        weatherCard.imageViewWeatherIcon.setImageResource(
                            getImageFromWeatherCode(
                                weatherCode
                            )
                        )
                    }
                }
            },
            onFailure = {
                Log.i("Weather", "failed $it")
            }
        )
    }

    private fun convertDateFormat(dateTime: String): String {
        val formatterInput = DateTimeFormatter.ISO_DATE_TIME
        val formatterOutput = DateTimeFormatter.ofPattern("EEE, dd MMMM yyyy", Locale.ENGLISH)
        val parsedDateTime = LocalDateTime.parse(dateTime, formatterInput)
        return formatterOutput.format(parsedDateTime)
    }

    private fun getImageFromWeatherCode(weatherCode: Int): Int {
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

    fun chooseClothes(){

    }
}