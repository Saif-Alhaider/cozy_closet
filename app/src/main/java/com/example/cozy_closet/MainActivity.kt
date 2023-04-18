package com.example.cozy_closet

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cozy_closet.databinding.ActivityMainBinding
import com.example.cozy_closet.models.ClothSuggester
import com.example.cozy_closet.models.Clothes
import com.example.cozy_closet.models.WeatherCodes
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
        PrefUtil.initSharedPrefs(applicationContext)
        presenter.getWeatherData()
    }

    override fun showClothes(clothes: Clothes) {
        TODO("Not yet implemented")
    }

//    private fun checkClothData(context: Context): Clothes {
//        val storedDress = PrefUtil.getStoredClothes().first.dress
//        val storedPants = PrefUtil.getStoredClothes().first.pants
//        val tempRange = PrefUtil.getStoredClothes().first.temperatureRange
//
//        return if (storedDress == null || storedPants == null || tempRange == null) {
////            setClothesData(temperature!!, null)
//            getClothesData().first
//        } else {
//
//            getClothesData().first
//        }
//    }



//    override fun showClothes(clothes: Clothes) {
//        TODO("Not yet implemented")
//    }


    override fun showWeatherData(weather:Weather,date:String,weatherDescription:String) {

        runOnUiThread {
            binding.weatherCard.run {
                textViewWeatherDescription.text = weatherDescription
                textViewDate.text = date
                textViewWeatherTemp.text = getString(R.string.temperature, weather.currentWeather.temperature.toString())
                imageViewWeatherIcon.setImageResource(
                    WeatherCodes.getWeatherIconFromWeatherCode(weather.currentWeather.weatherCode)
                )
            }
        }
    }

    override fun showNoNetworkConnection() {

    }





}