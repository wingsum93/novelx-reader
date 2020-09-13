package com.ericho.example.ext

import com.ericho.example.ui.novel.NovelObject
import com.ericho.example.ui.novel.chapter.Chapter
import org.jsoup.nodes.Document

typealias NovelHtmlFactoryOld = (Document) -> List<Chapter>
typealias NovelHtmlFactory = (Document) -> NovelObject

