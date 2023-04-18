package com.example.cozy_closet.models

import android.util.Range
import java.time.LocalDateTime

data class Clothes(
    val dress: Int?,
    val pants: Int?,
    val temperatureRange: IntRange?
)