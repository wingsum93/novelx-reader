package com.ericho.example.ui.novel

import com.ericho.example.ui.novel.chapter.Chapter

data class NovelObject(
    var title: String,
    var author: String,
    var description: String? = null,
    var lastUpdateTime: String? = null,
    val chapters: MutableList<Chapter> = mutableListOf()
) {
    var currentChapter: Chapter? = null
}