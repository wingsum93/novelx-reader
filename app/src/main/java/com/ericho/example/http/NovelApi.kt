package com.ericho.example.http

import retrofit2.http.GET
import retrofit2.http.Url

interface NovelApi {
    @GET
    suspend fun getChapterList(@Url url: String): String

    @GET
    suspend fun getPageContent(@Url url: String)
}