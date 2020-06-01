package com.ericho.example

import org.jsoup.Jsoup
import org.junit.Test

/**
 * Search Novel data in html source code
 */
class NovelDataTest {
    @Test
    fun check_computer_chapter_list() {
        println("check_computer_chapter_list")
        val map = mutableMapOf<String, String>()
        val map2 = mutableMapOf<String, Chapter>()
        val url_index = "https://tw.uukanshu.com/b/41496/"
        // format the required url
        val url = url_index
        val doc = Jsoup.connect(url).get()
        val cssQuery = "ul#chapterList li a"
        // for each chapters
        val elements = doc.select(cssQuery)
        elements.forEach {
            val aElement = it.select("a").first()
            val chapterName = aElement.html()
            val link = aElement.absUrl("href")
            map[chapterName] = link
        }
        println(map)
    }

    @Test
    fun check_mobile_chapter_list() {
        println("check_mobile_chapter_list")
        val map = mutableMapOf<String, String>()
//        val map2 = mutableMapOf<String,Chapter>()
        val url_index = "https://tw.uukanshu.com/b/41496/"
        val url_index2 = "https://t.uukanshu.com/book.aspx?id=41496"

        // format the required url
        val url = url_index2
        val doc = Jsoup.connect(url).get()
        val cssQuery = "ul#chapterList li a"
        // for each chapters
        val elements = doc.select(cssQuery)
        elements.forEach {
            val aElement = it.select("a").first()
            val chapterName = aElement.html()
            val link = aElement.absUrl("href")
            map[chapterName] = link
        }
        println(map)
    }
}
