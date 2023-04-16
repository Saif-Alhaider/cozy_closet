package com.example.cozy_closet.models

import java.time.LocalDateTime

data class Clothes(
    val dress: Int,
    val pants: Int,
    val _timeCreated: LocalDateTime = LocalDateTime.now()
) {
    val timeCreated: LocalDateTime
        get() = _timeCreated
}