package com.example.cozy_closet.models

import com.example.cozy_closet.R
import com.example.cozy_closet.util.Constants.coldTempRange
import com.example.cozy_closet.util.Constants.coolTempRange
import com.example.cozy_closet.util.Constants.hotTempRange

class ClothSuggester {
    private val outfits = listOf<Clothes>(
        Clothes(R.mipmap.sweater_2, R.mipmap.pants_1, coldTempRange),
        Clothes(R.mipmap.tshirt_2, R.mipmap.pants_1, coolTempRange),
        Clothes(R.mipmap.sweater_2, R.mipmap.pants_1, coldTempRange),
        Clothes(R.mipmap.tshirt_2, R.mipmap.short_1, hotTempRange),
        Clothes(R.mipmap.tshirt_3, R.mipmap.short_1, hotTempRange),
        Clothes(R.mipmap.tshirt_4, R.mipmap.short_1, hotTempRange)
    )

    fun suggestClothes(currentTemperature: Double, currentClothes: Clothes?): Clothes {
        return when (currentClothes) {
            null -> outfits.filter {
                currentTemperature.toInt() in (it.temperatureRange ?: IntRange.EMPTY)
            }
            else -> outfits.filter {
                it != currentClothes &&
                        currentTemperature.toInt() in (it.temperatureRange ?: IntRange.EMPTY)
            }
        }.random()

    }
}