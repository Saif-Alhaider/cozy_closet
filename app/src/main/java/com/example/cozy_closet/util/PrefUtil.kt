package com.example.cozy_closet.util

import android.content.Context
import android.content.SharedPreferences
import com.example.cozy_closet.models.Clothes
import java.time.LocalDateTime


object PrefUtil {
    private const val SHARE_PREFS_NAME = "MySharedPrefs"
    private const val DRESS = "DRESS"
    private const val PANTS = "PANTS"
    private const val TIME_CREATED = "TIME CREATED"
    private const val TEMPERATURE_RANGE_INITIAL = "TEMPERATURE RANGE INITIAL"
    private const val TEMPERATURE_RANGE_FINAL = "TEMPERATURE RANGE FINAL"
    private var sharedPreferences: SharedPreferences? = null

    fun initSharedPrefs(context: Context) {
        sharedPreferences = context.getSharedPreferences(SHARE_PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun storeClothes(clothes: Clothes) {
        sharedPreferences?.edit()?.apply {
            putInt(DRESS, clothes.dressResourceId!!)
            putInt(PANTS, clothes.pantsResourceId!!)
            putInt(TEMPERATURE_RANGE_INITIAL, clothes.temperatureRange!!.first)
            putInt(TEMPERATURE_RANGE_FINAL, clothes.temperatureRange.last)
            putString(TIME_CREATED, LocalDateTime.now().toString())
            apply()
        }
    }

    fun getStoredClothes(): Pair<Clothes, String?> {
        val dressId = sharedPreferences?.getInt(DRESS, 0)
        val pantsId = sharedPreferences?.getInt(PANTS, 0)
        val tempRangeInit = sharedPreferences?.getInt(TEMPERATURE_RANGE_INITIAL, 0)
        val tempRangeFinal = sharedPreferences?.getInt(TEMPERATURE_RANGE_FINAL, 0)
        val timeCreated = sharedPreferences?.getString(TIME_CREATED, null)
        val tempRange = tempRangeInit?.let { start ->
            tempRangeFinal?.let { end ->
                start..end
            }
        }
        return Pair(Clothes(dressId, pantsId, tempRange), timeCreated)
    }
}