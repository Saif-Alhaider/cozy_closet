package com.example.cozy_closet.models

import java.time.LocalDateTime

data class Clothes(
    val dress: Int?,
    val pants: Int?,
    val timeCreated: String? = LocalDateTime.now().toString()
)