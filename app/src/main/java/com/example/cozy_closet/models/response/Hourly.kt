package com.example.cozy_closet.models.response

data class Hourly(
    val time:List<String>,
    val temperature_80m:List<Double>
)
