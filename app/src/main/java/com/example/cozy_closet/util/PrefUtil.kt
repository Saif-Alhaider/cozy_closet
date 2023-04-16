package com.example.cozy_closet.util

import android.content.Context
import android.content.SharedPreferences
import com.example.cozy_closet.models.Clothes
import com.google.gson.Gson


object PrefUtil {
    private const val SHARE_PREFS_NAME = "MySharedPrefs"
    private const val CLOTH_OBJECT = "ClothObject"
    private var sharedPreferences: SharedPreferences? = null

    fun initSharedPrefs(context: Context) {
        sharedPreferences = context.getSharedPreferences(SHARE_PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun storeClothes(clothes: Clothes) {
        val clothesString = Gson().toJson(clothes)
        sharedPreferences?.edit()?.putString(CLOTH_OBJECT, clothesString)?.apply()
    }
}