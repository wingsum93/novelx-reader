package com.ericho.example.data.repo.remote

import androidx.core.net.toUri
import com.ericho.example.data.INovelRepository
import com.ericho.example.ext.DocumentHelper
import com.ericho.example.ext.NovelHtmlFactory
import com.ericho.example.http.NovelApi
import com.ericho.example.ui.novel.NovelObject
import com.ericho.example.ui.novel.chapter.Chapter
import com.google.gson.Gson
import timber.log.Timber
import java.io.IOException

class NovelRemoteDataSource(
    private val api: NovelApi,
    private val helper: DocumentHelper
) : INovelRepository {
    //diff domain, diff factory
    private val factoryMap: MutableMap<String, NovelHtmlFactory> = mutableMapOf()
    private lateinit var uukanFactory: NovelHtmlFactory
    private val gson = Gson()

    init {

        uukanFactory = { url, fullDocument ->
            fullDocument.setBaseUri(url)
            val res = mutableListOf<Chapter>()
            val cssQuery_doc = "div.xiaoshuo_content"
            val cssQuery_title = "h1 a"
            val cssQuery_author = "h2 a"
            val cssQuery_description = "h3 p"
            val cssQuery_update_time = "div.shijian"
            val cssQuery_chapter = "ul#chapterList li a"
            //
            val doc = fullDocument.select(cssQuery_doc)
            val novelTitle = doc.select(cssQuery_title).first().html()
            val novelAuthor = doc.select(cssQuery_author).html()
            val novelDescription = doc.select(cssQuery_description).html()
            val novelUpdateTime = doc.select(cssQuery_update_time).text()
                .split("没有更新？告诉管理員更新")[0]

            // for each chapters
            val elements = fullDocument.select(cssQuery_chapter)
            elements.forEach {
                val aElement = it
                val chapterName = aElement.html()
                val link = aElement.absUrl("href")
                val chapter = Chapter(title = chapterName, link = link)
                res += chapter
            }

            NovelObject(
                title = novelTitle,
                author = novelAuthor,
                description = novelDescription,
                lastUpdateTime = novelUpdateTime,
                chapters = res
            )
        }
        factoryMap["www.uukanshu.com"] = uukanFactory
        factoryMap["tw.uukanshu.com"] = uukanFactory
        factoryMap["t.uukanshu.com"] = uukanFactory
    }


    override suspend fun getNovelData(urls: String): NovelObject {
        val uri = urls.toUri()
        val key = uri.host
        val result = api.getPageContent(urls)
        return if (result.isSuccessful) {
            Timber.i("should have result")
            val string = result.body()?.string()
            uukanFactory.invoke(
                urls,
                helper.convertStringToDocument(html = string ?: "")
            )
        } else {
            throw IOException("error")
        }

    }
}