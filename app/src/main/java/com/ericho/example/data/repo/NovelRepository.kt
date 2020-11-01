package com.ericho.example.data.repo

import com.ericho.example.data.INovelRepository
import com.ericho.example.other.NovelSharePreferenceHelper
import com.ericho.example.ui.novel.NovelObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class NovelRepository(
    private val remoteRepository: INovelRepository,
    private val localRepository: INovelRepository,
    private val novelSharePreferenceHelper: NovelSharePreferenceHelper
) : INovelRepository {

    @Throws(IOException::class)
    override suspend fun getNovelData(urls: String): NovelObject {
        val a = kotlin.runCatching { remoteRepository.getNovelData(urls) }
        if (a.isSuccess) {
            a.getOrThrow()
        }
        return withContext(Dispatchers.IO) {

            val novelObject = remoteRepository.getNovelData(urls)
            val currentChapter = novelSharePreferenceHelper.getCurrentChapter(novelObject.title)
            novelObject.currentChapter = currentChapter
            novelObject
        }
    }
}