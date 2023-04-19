package com.example.cozy_closet

import com.example.cozy_closet.models.CardData
import com.example.cozy_closet.models.Clothes
import com.example.cozy_closet.models.response.Weather

interface MainActivityView {
    fun showClothes(clothes:Clothes)
    fun showWeatherData(cardData: CardData)
    fun showNoNetworkConnection(show:Boolean)
    fun showLoadingScreen(show:Boolean)
}