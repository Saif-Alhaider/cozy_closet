package com.example.cozy_closet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cozy_closet.databinding.ActivityMainBinding
import com.example.cozy_closet.models.Clothes
import com.example.cozy_closet.models.WeatherCodes
import com.example.cozy_closet.models.response.Weather
import com.example.cozy_closet.util.PrefUtil
import com.example.cozy_closet.util.hide
import com.example.cozy_closet.util.show

class MainActivity : AppCompatActivity(), MainActivityView {
    private lateinit var binding: ActivityMainBinding
    private val presenter: Presenter by lazy { Presenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        PrefUtil.initSharedPrefs(applicationContext)
        binding.errorScreen.screen.hide()
        presenter.getData()
    }

    override fun showClothes(clothes: Clothes) {
        runOnUiThread {
            binding.run {
                itemDress.imageViewCloth.setImageResource(clothes.dressResourceId!!)
                itemPants.imageViewCloth.setImageResource(clothes.pantsResourceId!!)
            }
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
                binding.loadingScreen.screen.hide()
                binding.errorScreen.screen.hide()
            }

        }
    }

    override fun showNoNetworkConnection() {
        binding.run {
            loadingScreen.screen.hide()
            errorScreen.screen.show()
        }
        reloadButtonCallBack()
    }
    private fun reloadButtonCallBack(){
        binding.errorScreen.reloadScreen.setOnClickListener {
            presenter.getData()
        }
    }

}