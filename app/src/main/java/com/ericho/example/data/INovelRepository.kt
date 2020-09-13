package com.ericho.example.data

import com.ericho.example.ui.novel.NovelObject
import java.io.IOException

interface INovelRepository {

    @Throws(IOException::class)
    suspend fun getNovelData(urls: String): NovelObject
}