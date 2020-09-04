package com.ericho.example.http

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object OkManager {

    private val okhttpClient: OkHttpClient = OkHttpClient.Builder()
        .readTimeout(15, TimeUnit.SECONDS)
        .connectTimeout(25, TimeUnit.SECONDS)
        .addNetworkInterceptor(MobileItercepter())
        .cache(null)
        .build()


    fun getClient(): OkHttpClient {
        return okhttpClient
    }

}