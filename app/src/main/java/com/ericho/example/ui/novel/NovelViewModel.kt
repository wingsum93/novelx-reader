package com.ericho.example.ui.novel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ericho.example.data.Chapter
import com.ericho.example.ext.post
import com.ericho.example.ui.util.NovelLinkConverter

class NovelViewModel : ViewModel() {

    var indexLink: String = ""
    val indexOrDetail = ObservableBoolean(true)
    var currentChapter: MutableLiveData<Chapter> = MutableLiveData()

    //data
    private val data: MutableList<Chapter> = mutableListOf()


    fun loadAllInfo(l: String) {
        indexLink = l
        data += NovelLinkConverter.getListOfChapter_mobile(indexLink)
        indexOrDetail post true

    }

    fun getToPrevChapter() {
        currentChapter post Chapter("", "")
    }

    fun getToNextChapter() {
        currentChapter post Chapter("", "")
    }
}