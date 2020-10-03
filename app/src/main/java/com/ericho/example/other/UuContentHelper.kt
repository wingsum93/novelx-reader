package com.ericho.example.other

import com.ericho.example.ui.novel.NovelObject
import com.ericho.example.ui.novel.chapter.Chapter
import org.jsoup.nodes.Document
import java.net.URL

class UuContentHelper {
    /**
     * Convert to object for all supported website
     *
     * @param url the novel home page
     * @param document the novel chapter page html
     * @return handled data
     */
    fun convert(url: String, document: Document): NovelObject {
        val temp = URL(url)
        return when (temp.host) {
            // pc website
            "tw.uukanshu.com" -> convert_for_uukanshu_tw(url, document)
            "www.uukanshu.com" -> convert_for_uukanshu_tw(url, document)
            // mobile website
            "t.uukanshu.com" -> convert_aaaaa(url, document)
            else -> throw UnsupportedOperationException()
        }
    }

    /**
     * for https://t.uukanshu.com/book.aspx?id=41496
     *
     * @param document
     * @return
     */
    private fun convert_aaaaa(url: String, document: Document): NovelObject {
        val cssQuery = "div.ml-list ul li"
        val elements = document.select(cssQuery)
        val res = mutableListOf<Chapter>()
        elements.forEach {
            val aElement = it
            val string = aElement.html()
//            res .add(string)
        }
        return NovelObject(
            title = "",
            author = "",
            chapters = res
        )
    }

    /**
     * for https://tw.uukanshu.com/b/93912/   https://www.uukanshu.com/b/93912/
     *
     * @param url
     * @param document
     * @return
     */
    private fun convert_for_uukanshu_tw(url: String, document: Document): NovelObject {
        document.setBaseUri(url)
        val res = mutableListOf<Chapter>()
        val cssQuery_doc = "div.xiaoshuo_content"
        val cssQuery_title = "h1 a"
        val cssQuery_author = "h2 a"
        val cssQuery_description = "h3 p"
        val cssQuery_update_time = "div.shijian"
        val cssQuery_chapter = "ul#chapterList li a"
        //
        val doc = document.select(cssQuery_doc)
        val novelTitle = doc.select(cssQuery_title).first().html()
        val novelAuthor = doc.select(cssQuery_author).html()
        val novelDescription = doc.select(cssQuery_description).html()
        val novelUpdateTime = doc.select(cssQuery_update_time).text()
            .split("没有更新？告诉管理員更新")[0]

        // for each chapters
        val elements = document.select(cssQuery_chapter)
        elements.forEach {
            val aElement = it
            val chapterName = aElement.html()
            val link = aElement.absUrl("href")
            val chapter = Chapter(title = chapterName, link = link)
            res += chapter
        }

        return NovelObject(
            title = novelTitle,
            author = novelAuthor,
            description = novelDescription,
            lastUpdateTime = novelUpdateTime,
            chapters = res
        )
    }
}