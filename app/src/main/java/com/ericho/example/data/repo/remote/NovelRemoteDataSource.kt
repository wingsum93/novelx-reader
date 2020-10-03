package com.ericho.example.data.repo.remote

import com.ericho.example.data.INovelRepository
import com.ericho.example.ext.DocumentHelper
import com.ericho.example.http.NovelApi
import com.ericho.example.other.UuContentHelper
import com.ericho.example.ui.novel.NovelObject
import timber.log.Timber
import java.io.IOException

class NovelRemoteDataSource(
    private val api: NovelApi,
    private val helper: DocumentHelper,
    private val uuContentHelper: UuContentHelper
) : INovelRepository {


    override suspend fun getNovelData(urls: String): NovelObject {
        val result = api.getPageContent(urls)
        if (result.isSuccessful) {
            Timber.i("should have result")
            val string = result.body()?.string()
            // from html string to processed data
            val document = helper.convertStringToDocument(html = string ?: "")
            val novelObject: NovelObject = uuContentHelper.convert(urls, document)
            return novelObject.copy(chapters = novelObject.chapters.reversed().toMutableList())
        } else {
            throw IOException("error")
        }
    }
}