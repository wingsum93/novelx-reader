package com.ericho.example.logic

import com.ericho.example.ui.novel.chapter.Chapter
import org.jsoup.nodes.Document

class NovelExtractLogic {


    fun getChapterDisplayModel(doc: Document): List<Chapter> {
        val res = mutableListOf<Chapter>()
        val cssQuery = "ul#chapterList li a"
        // for each chapters
        val elements = doc.select(cssQuery)
        elements.forEach {
            val aElement = it.select("a").first()
            val chapterName = aElement.html()
            val link = aElement.absUrl("href")

            val chapter = Chapter(title = chapterName, link = link)
            res += chapter
        }
        return res
    }
}