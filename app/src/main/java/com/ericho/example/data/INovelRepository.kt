package com.ericho.example.data

import com.ericho.example.ui.novel.NovelObject
import java.io.IOException

interface INovelRepository {
    /**
     * get Novel's info
     *
     * @param urls the url of that novel
     * @throws IOException when somethings wrong
     * @return the novel object, either new, or cached
     */
    @Throws(IOException::class)
    suspend fun getNovelData(urls: String): NovelObject
}