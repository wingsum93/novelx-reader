package com.ericho.example.data.repo.remote

import com.ericho.example.data.INovelRepository
import com.ericho.example.http.NovelApi
import com.ericho.example.ui.novel.chapter.ChapterDisplayModel
import com.ericho.example.ui.util.NovelLinkConverter

class NovelRemoteDataSource(
    private val api: NovelApi
) : INovelRepository {


    override suspend fun getChapters(urls: String): List<ChapterDisplayModel> {

        return NovelLinkConverter.getListOfChapter_mobile(urls)
    }
}