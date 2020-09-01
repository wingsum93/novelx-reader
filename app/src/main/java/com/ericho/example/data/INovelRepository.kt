package com.ericho.example.data

import com.ericho.example.ui.novel.chapter.ChapterDisplayModel

interface INovelRepository {

    suspend fun getChapters(urls: String): List<ChapterDisplayModel>
}