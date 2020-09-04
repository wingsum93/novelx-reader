package com.ericho.example.http

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class MobileItercepter : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder();
        val url = chain.request().url
        Timber.i("url: -> $url")
        //adding a header to the original request
        requestBuilder.addHeader("X-Been", "Intercepted");

        //cahnging the response
        val response = chain.proceed(requestBuilder.build());
        return response

    }
}