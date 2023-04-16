package com.example.cozy_closet

import okhttp3.Response

interface MainActivityView {
    fun onSuccess(response: Response)
    fun onFailure(message: String?)
}