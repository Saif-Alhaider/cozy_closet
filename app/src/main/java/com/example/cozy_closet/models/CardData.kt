package com.example.cozy_closet.models

import com.example.cozy_closet.models.response.Weather

data class CardData(
    val weather: Weather,
    val date: String? = "Date",
    val weatherDescription: String? = "Weather Description"
)