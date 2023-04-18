package com.example.cozy_closet.models

import com.example.cozy_closet.R

class ClothSuggester {
    private val outfits = listOf<Clothes>(
        Clothes(R.mipmap.sweater_2, R.mipmap.pants_1, -10..10),
        Clothes(R.mipmap.tshirt_2, R.mipmap.short_1, 19..40)
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