package com.ericho.example.logic

import com.ericho.example.ui.novel.chapter.ChapterDisplayModel
import org.jsoup.nodes.Document

class NovelExtractLogic {


    fun getChapterDisplayModel(doc: Document): List<ChapterDisplayModel> {
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
        return res
    }
}