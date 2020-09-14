package com.ericho.example

import com.ericho.example.ext.NovelHtmlFactory
import com.ericho.example.ui.novel.NovelObject
import com.ericho.example.ui.novel.chapter.Chapter
import org.jsoup.Jsoup
import org.junit.Assert
import org.junit.Test

/**
 * Search Novel data in html source code
 */
class NovelDataTest {
    private var uukanFactory: NovelHtmlFactory

    init {
        uukanFactory = { url, fullDocument ->
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
    }

    @Test
    fun check_computer_chapter_list() {
        println("check_computer_chapter_list")
        // format the required url
        val url = Cc.SampleUrl.LINK_A
        val document = Jsoup.connect(url).get()
        val r = uukanFactory.invoke(url, document)

        println(r)
        Assert.assertTrue(r.chapters.isNotEmpty())
    }

    @Test
    fun check_mobile_chapter_list() {
        println("check_mobile_chapter_list")
        val map = mutableMapOf<String, String>()
        val url = Cc.SampleUrl.LINK_A_M
        val document = Jsoup.connect(url).get()
        val r = uukanFactory.invoke(url, document)

        println(map)
        Assert.assertTrue(r.chapters.isNotEmpty())
    }
}
