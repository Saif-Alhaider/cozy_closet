package com.example.cozy_closet.models.network

import android.util.Log
import com.example.cozy_closet.models.response.Weather
import com.example.cozy_closet.models.request.WeatherRequest
import com.google.gson.Gson
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

class WeatherService {
    private val httpClient: OkHttpClient by lazy {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    fun getWeather(weatherRequest: WeatherRequest) {
        val url = HttpUrl
            .Builder()
            .scheme("https")
            .host("api.open-meteo.com")
            .addPathSegment("v1")
            .addPathSegment("forecast")
            .addQueryParameter("latitude", weatherRequest.latitude.toString())
            .addQueryParameter("longitude", weatherRequest.longitude.toString())
            .addQueryParameter("hourly", weatherRequest.hourly)
            .addQueryParameter("current_weather", weatherRequest.current_weather.toString())
            .build()

        val request = Request.Builder().url(url).build()

        httpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i("Weather", "fail ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    val result = Gson().fromJson(jsonString, Weather::class.java)
                    Log.i("Weather", result.toString())
                }
            }
        }
        )
    }
}