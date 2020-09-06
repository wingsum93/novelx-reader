package com.ericho.example.other

import okhttp3.ResponseBody
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import retrofit2.Converter


class HtmlPageAdapter : Converter<ResponseBody, Page> {
    override fun convert(responseBody: ResponseBody): Page? {
        val document: Document = Jsoup.parse(responseBody.string())
        val value: Element = document.select("script")[1]
        val content: String = value.html()
        return Page(content)
    }

}