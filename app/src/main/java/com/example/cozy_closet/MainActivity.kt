package com.example.cozy_closet

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cozy_closet.databinding.ActivityMainBinding
import com.example.cozy_closet.models.Clothes
import com.example.cozy_closet.models.WeatherCodes
import com.example.cozy_closet.models.network.WeatherService
import com.example.cozy_closet.models.request.WeatherRequest
import com.example.cozy_closet.models.response.Weather
import com.example.cozy_closet.util.PrefUtil
import com.google.gson.Gson
import okhttp3.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity(), MainActivityView {
    private lateinit var binding: ActivityMainBinding
    private val presenter: Presenter by lazy { Presenter(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setWeatherData()
        Log.i("Clothes_Data", suggestClothes(this).toString())
    }

    private fun suggestClothes(context: Context): Clothes {
        val storedDress = PrefUtil.getStoredClothes().dress
        val storedPants = PrefUtil.getStoredClothes().pants

        val prefs = PrefUtil.initSharedPrefs(context)
        return if (storedDress == null || storedPants == null) {
            setClothesData()
            getClothesData()
        } else {
            getClothesData()
        }
    }

    private fun setClothesData() {
        PrefUtil.storeClothes(
            Clothes(
                dress = R.mipmap.pants_1,
                pants = R.mipmap.sweater_2,
                timeCreated = LocalDateTime.now().toString()
            )
        )
    }

    private fun getClothesData(): Clothes = PrefUtil.getStoredClothes()


    private fun setWeatherData() {
        val weatherRequest: WeatherRequest = WeatherRequest(
            latitude = 33.23, longitude = 44.33, hourly = "temperature_80m", current_weather = true
        )
        WeatherService().getWeather(
            weatherRequest, onSuccess = ::onSuccess, onFailure = ::onFailure
        )
    }


    override fun onSuccess(response: Response) {
        val result = Gson().fromJson(response.body?.string().toString(), Weather::class.java)

        runOnUiThread {
            val weatherCode = result.currentWeather.weatherCode
            val temperature = result.currentWeather.temperature
            setCardData(weatherCode, temperature)
        }
    }

    override fun onFailure(message: String?) {
        Log.i("Weather", "failed $message")
    }

    private fun setCardData(weatherCode: Int, temperature: Double) {
        val currentTime = LocalDateTime.now().toString()
        val date = convertDateFormat(currentTime)
        val weatherDescription = WeatherCodes.getCodeDescription(weatherCode)

        binding.weatherCard.run {
            textViewWeatherDescription.text = weatherDescription
            textViewDate.text = date
            textViewWeatherTemp.text = getString(R.string.temperature, temperature.toString())
            imageViewWeatherIcon.setImageResource(
                WeatherCodes.getImageFromWeatherCode(weatherCode)
            )
        }
    }

    private fun convertDateFormat(dateTime: String): String {
        val formatterInput = DateTimeFormatter.ISO_DATE_TIME
        val formatterOutput = DateTimeFormatter.ofPattern("EEE, dd MMMM yyyy", Locale.ENGLISH)
        val parsedDateTime = LocalDateTime.parse(dateTime, formatterInput)
        return formatterOutput.format(parsedDateTime)
    }

}