package com.ericho.example.data.repo

import com.ericho.example.data.INovelRepository
import com.ericho.example.ui.novel.chapter.ChapterDisplayModel

class NovelRepository(
    private val remoteRepository: INovelRepository,
    private val localRepository: INovelRepository
) : INovelRepository {
    override suspend fun getChapters(urls: String): List<ChapterDisplayModel> {
        return remoteRepository.getChapters(urls)
    }


}