package com.ericho.example.data.repo

import com.ericho.example.data.INovelRepository
import com.ericho.example.ui.novel.NovelObject

class NovelRepository(
    private val remoteRepository: INovelRepository,
    private val localRepository: INovelRepository
) : INovelRepository {

    override suspend fun getNovelData(urls: String): NovelObject {
        return remoteRepository.getNovelData(urls)
    }
}