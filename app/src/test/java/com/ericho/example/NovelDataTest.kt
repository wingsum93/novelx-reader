package com.ericho.example

import com.ericho.example.other.UuContentHelper
import org.jsoup.Jsoup
import org.junit.Assert
import org.junit.Test

/**
 * Search Novel data in html source code
 */
class NovelDataTest {
    private val uuContentHelper = UuContentHelper()


    @Test
    fun `check computer chapter list`() {
        println("check_computer_chapter_list")
        // format the required url
        val url = Cc.SampleUrl.LINK_A
        val document = Jsoup.connect(url).get()
        val r = uuContentHelper.convert(url, document)

        println(r)
        Assert.assertTrue(r.chapters.isNotEmpty())
    }

    @Test
    fun `check mobile chapter list`() {
        println("check_mobile_chapter_list")
        val map = mutableMapOf<String, String>()
        val url = Cc.SampleUrl.LINK_A_M
        val document = Jsoup.connect(url).get()
        val r = uuContentHelper.convert(url, document)

        println(map)
        Assert.assertTrue(r.chapters.isNotEmpty())
    }

    @Test
    fun `Page Content`() {
        val url = Cc.SampleUrl.LINK_CONTENT_11
        val document = Jsoup.connect(url).get()
        val r = uuContentHelper.convert(url, document)
        Assert.assertTrue(r.chapters.isNotEmpty())
    }
}
