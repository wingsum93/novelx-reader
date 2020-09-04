package com.ericho.example.ext

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class DocumentHelper {

    fun convertStringToDocument(html: String): Document = Jsoup.parse(html)
}