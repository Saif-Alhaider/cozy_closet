package com.example.cozy_closet.models.network

import okhttp3.*
import java.io.IOException

abstract class BaseService {
    abstract val client: OkHttpClient

    fun makeRequestCall(
        request: Request,
        onSuccess: (response: Response) -> Unit,
        onFailure: (message:String?) -> Unit
        ) {
        client.newCall(request).enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException)  = onFailure(e.message)

            override fun onResponse(call: Call, response: Response)  = onSuccess(response)
        })
    }
}