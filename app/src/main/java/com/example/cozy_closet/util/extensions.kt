package com.example.cozy_closet.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun String.toLocalDateTime(): LocalDateTime? {
    return try {
        LocalDateTime.parse(this)
    } catch (e: DateTimeParseException) {
        throw Exception(e)
    }
}