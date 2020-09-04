package com.ericho.example.ext

import com.ericho.example.ui.novel.chapter.ChapterDisplayModel
import org.jsoup.nodes.Document

typealias NovelHtmlFactory = (Document) -> List<ChapterDisplayModel>

