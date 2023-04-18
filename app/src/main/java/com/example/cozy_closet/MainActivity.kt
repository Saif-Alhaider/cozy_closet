package com.example.cozy_closet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cozy_closet.databinding.ActivityMainBinding
import com.example.cozy_closet.models.Clothes
import com.example.cozy_closet.models.WeatherCodes
import com.example.cozy_closet.models.response.Weather
import com.example.cozy_closet.util.PrefUtil

class MainActivity : AppCompatActivity(), MainActivityView {
    private lateinit var binding: ActivityMainBinding
    private val presenter: Presenter by lazy { Presenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        PrefUtil.initSharedPrefs(applicationContext)
        Log.i("Weather_Data", PrefUtil.getStoredClothes().toString())
        presenter.getData()
    }

    override fun showClothes(clothes: Clothes) {
        runOnUiThread{
            binding.itemDress.imageViewCloth.setImageResource(clothes.dress!!)
            binding.itemPants.imageViewCloth.setImageResource(clothes.pants!!)
        }

    }


    override fun showWeatherData(weather: Weather, date: String, weatherDescription: String) {

        runOnUiThread {
            binding.weatherCard.run {
                textViewWeatherDescription.text = weatherDescription
                textViewDate.text = date
                textViewWeatherTemp.text =
                    getString(R.string.temperature, weather.currentWeather.temperature.toString())
                imageViewWeatherIcon.setImageResource(
                    WeatherCodes.getWeatherIconFromWeatherCode(weather.currentWeather.weatherCode)
                )
            }
        }
    }

    override fun showNoNetworkConnection() {

    }


}