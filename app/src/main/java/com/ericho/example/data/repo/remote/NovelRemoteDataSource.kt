package com.ericho.example.data.repo.remote

import androidx.core.net.toUri
import com.ericho.example.data.INovelRepository
import com.ericho.example.ext.DocumentHelper
import com.ericho.example.ext.NovelHtmlFactory
import com.ericho.example.http.NovelApi
import com.ericho.example.ui.novel.chapter.ChapterDisplayModel
import com.google.gson.Gson
import timber.log.Timber

class NovelRemoteDataSource(
    private val api: NovelApi,
    private val helper: DocumentHelper
) : INovelRepository {
    //diff domain, diff factory
    private val factoryMap: MutableMap<String, NovelHtmlFactory> = mutableMapOf()
    private lateinit var default: NovelHtmlFactory
    private val gson = Gson()

    init {
        default = { doc ->
            val res = mutableListOf<ChapterDisplayModel>()
            val cssQuery = "ul#chapterList li a"
            // for each chapters
            val elements = doc.select(cssQuery)
            elements.forEach {
                val aElement = it.select("a").first()
                val chapterName = aElement.html()
                val link = aElement.absUrl("href")

                val chapter = ChapterDisplayModel(title = chapterName, link = link)
                res += chapter
            }
            res
        }
        val uukanFactory = default
        factoryMap["www.uukanshu.com"] = uukanFactory
        factoryMap["tw.uukanshu.com"] = uukanFactory
        factoryMap["t.uukanshu.com"] = uukanFactory
    }

    override suspend fun getChapters(url: String): List<ChapterDisplayModel> {
        val uri = url.toUri()
        val key = uri.host
        Timber.i("key: $key url:$url")
        val ff = factoryMap[key] ?: default
        //
        val result = api.getPageContent(url)
        return if (result.isSuccessful) {
            Timber.i("should have result")
            val string = result.body()?.string()
            ff.invoke(helper.convertStringToDocument(html = string ?: ""))
        } else {
            Timber.i("request is fail")
            emptyList()
        }
    }
}