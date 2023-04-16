package com.example.cozy_closet.models.response

import com.google.gson.annotations.SerializedName

data class HourlyUnits(
    @SerializedName("time") val time:String,
    @SerializedName("temperature_80m") val temperature_80m:String
)
