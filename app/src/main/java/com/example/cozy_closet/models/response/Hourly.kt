package com.example.cozy_closet.models.response

import com.google.gson.annotations.SerializedName

data class Hourly(
    @SerializedName("time") val time:List<String>,
    @SerializedName("temperature_80m") val temperature_80m:List<Double>
)
