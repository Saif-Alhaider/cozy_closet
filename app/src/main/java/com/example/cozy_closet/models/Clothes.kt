package com.example.cozy_closet.models

import androidx.annotation.DrawableRes

data class Clothes(
    @DrawableRes val dressResourceId: Int?,
    @DrawableRes val pantsResourceId: Int? ,
    val temperatureRange: IntRange?
)