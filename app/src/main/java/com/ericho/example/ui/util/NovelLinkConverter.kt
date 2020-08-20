package com.ericho.example.ui.util

import com.ericho.example.ui.novel.ChapterDisplayModel
import org.jsoup.Jsoup

/**
 * Convert link for uukanshu.com
 */
object NovelLinkConverter {

    suspend fun getListOfChapter_pc(indexLink: String): List<ChapterDisplayModel> {
        val res = mutableListOf<ChapterDisplayModel>()

        val url = indexLink
        val doc = Jsoup.connect(url).get()
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
        return res
    }

    suspend fun getListOfChapter_mobile(indexLink: String): List<ChapterDisplayModel> {
        val res = mutableListOf<ChapterDisplayModel>()

        val url = indexLink
        val doc = Jsoup.connect(url).get()
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
        return res
    }

}