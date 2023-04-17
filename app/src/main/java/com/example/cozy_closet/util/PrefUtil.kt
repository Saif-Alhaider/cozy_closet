package com.example.cozy_closet.util

import android.content.Context
import android.content.SharedPreferences
import com.example.cozy_closet.models.Clothes
import com.google.gson.Gson
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


object PrefUtil {
    private const val SHARE_PREFS_NAME = "MySharedPrefs"
    private const val DRESS = "DRESS"
    private const val PANTS = "PANTS"
    private const val TIME_CREATED = "TIME CREATED"
    private var sharedPreferences: SharedPreferences? = null

    fun initSharedPrefs(context: Context) {
        sharedPreferences = context.getSharedPreferences(SHARE_PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun storeClothes(clothes: Clothes) {
        sharedPreferences?.edit()?.putInt(DRESS, clothes.dress!!)?.apply()
        sharedPreferences?.edit()?.putInt(PANTS, clothes.pants!!)?.apply()
        sharedPreferences?.edit()?.putString(TIME_CREATED, clothes.timeCreated)?.apply()

    }

    fun getStoredClothes(): Clothes {
        val dressId = sharedPreferences?.getInt(DRESS, 0)
        val pantsId = sharedPreferences?.getInt(PANTS, 0)
        val timeCreated = sharedPreferences?.getString(TIME_CREATED, null)

//        val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
//        val parsedTime = LocalDateTime.parse(timeCreated, formatter)
        return Clothes(dressId, pantsId, timeCreated)
    }
}