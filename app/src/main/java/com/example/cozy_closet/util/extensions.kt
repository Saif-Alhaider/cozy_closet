package com.example.cozy_closet.util

import android.view.View
import java.time.LocalDateTime
import java.time.format.DateTimeParseException

fun String.toLocalDateTime(): LocalDateTime? {
    return try {
        LocalDateTime.parse(this)
    } catch (e: DateTimeParseException) {
        throw Exception(e)
    }
}

fun View.show(){
    visibility = View.VISIBLE
}

fun View.hide(){
    visibility = View.GONE
}