package com.example.cozy_closet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cozy_closet.databinding.ActivityMainBinding
import com.example.cozy_closet.models.CardData
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
        presenter.fetchWeatherAndShowOutfit()
    }

    override fun showClothes(clothes: Clothes) {
        runOnUiThread {
            binding.run {
                itemDress.imageViewCloth.setImageResource(clothes.dressResourceId!!)
                itemPants.imageViewCloth.setImageResource(clothes.pantsResourceId!!)
            }
        }

    }


    override fun showWeatherData(cardData: CardData) {
        runOnUiThread {
            binding.weatherCard.run {
                textViewWeatherDescription.text = cardData.weatherDescription
                textViewDate.text = cardData.date
                textViewWeatherTemp.text =
                    getString(
                        R.string.temperature,
                        cardData.weather.currentWeather.temperature.toString()
                    )
                imageViewWeatherIcon.setImageResource(
                    WeatherCodes.getWeatherIconFromWeatherCode(cardData.weather.currentWeather.weatherCode)
                )
            }

        }
    }

    override fun showNoNetworkConnection(show: Boolean) {
        runOnUiThread {
            binding.errorScreen.screen.run {
                if (show) {
                    show()
                    reloadButtonCallBack()
                } else {
                    hide()
                }
            }


        }

    }

    override fun showLoadingScreen(show: Boolean) {
        runOnUiThread {
            binding.loadingScreen.screen.run { if (show) show() else hide() }
        }
    }


    private fun reloadButtonCallBack() {
        Log.i("working", true.toString())
        binding.errorScreen.reloadScreen.setOnClickListener {
            presenter.fetchWeatherAndShowOutfit()
        }
    }

}