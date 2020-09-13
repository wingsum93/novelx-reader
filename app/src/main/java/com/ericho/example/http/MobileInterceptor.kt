package com.ericho.example.http

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class MobileInterceptor : Interceptor {
    private val gson = Gson()

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder();
        val url = chain.request().url
        Timber.i("url: -> $url")
        //adding a header to the original request
        requestBuilder.addHeader("X-Been", "Intercepted")

        //chaining the response
        val response = chain.proceed(requestBuilder.build())
        Timber.d(chain.request().url.toString())
        val isRedirect = response.isRedirect
        Timber.d("isRedirect: $isRedirect")
        return response

    }
}