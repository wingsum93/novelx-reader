package com.ericho.example.data.repo

import com.ericho.example.data.INovelRepository
import com.ericho.example.other.NovelSharePreferenceHelper
import com.ericho.example.ui.novel.NovelObject
import java.io.IOException

class NovelRepository(
    private val remoteRepository: INovelRepository,
    private val localRepository: INovelRepository,
    private val novelSharePreferenceHelper: NovelSharePreferenceHelper
) : INovelRepository {

    @Throws(IOException::class)
    override suspend fun getNovelData(urls: String): NovelObject {
        val novelObject = remoteRepository.getNovelData(urls)
        val currentChapter = novelSharePreferenceHelper.getCurrentChapter(novelObject.title)
        novelObject.currentChapter = currentChapter
        return novelObject
    }
}