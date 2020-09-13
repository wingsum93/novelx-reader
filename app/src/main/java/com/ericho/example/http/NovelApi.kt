package com.ericho.example.http

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface NovelApi {

    @GET
    suspend fun getPageContent(@Url url: String): Response<ResponseBody>
}