package com.example.cozy_closet.models.network

import android.util.Log
import com.example.cozy_closet.models.response.Weather
import com.example.cozy_closet.models.request.WeatherRequest
import com.google.gson.Gson
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

class WeatherService() : BaseService() {
    override val client: OkHttpClient by lazy {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    fun getWeather(
        weatherRequest: WeatherRequest,
        onFailure: (message: String?) -> Unit,
        onSuccess: (response: Response) -> Unit
    ) {
        val request = makeWeatherRequest(weatherRequest)
        makeRequestCall(request, onSuccess, onFailure)
    }


    private fun makeWeatherRequest(weatherRequest: WeatherRequest): Request {
        val url = HttpUrl
            .Builder()
            .scheme("https")
            .host("api.open-meteo.com")
            .addPathSegment("v1")
            .addPathSegment("forecast")
            .addQueryParameter("latitude", weatherRequest.latitude.toString())
            .addQueryParameter("longitude", weatherRequest.longitude.toString())
            .addQueryParameter("hourly", weatherRequest.hourly)
            .addQueryParameter("current_weather", weatherRequest.currentWeather.toString())
            .build()

        return Request.Builder().url(url).build()
    }
}