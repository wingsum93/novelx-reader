package com.ericho.example.http

interface NovelApi {
    suspend fun getChapterList()

    suspend fun getPageContent()
}