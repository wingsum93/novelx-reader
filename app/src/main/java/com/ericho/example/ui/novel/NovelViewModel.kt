package com.ericho.example.ui.novel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ericho.example.data.INovelRepository
import com.ericho.example.ext.post
import com.ericho.example.ui.util.NovelLinkConverter
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NovelViewModel(
    private val repository: INovelRepository
) : ViewModel() {
    var indexLink: String = ""
    val indexOrDetail = MutableLiveData(true)
    var chapterDisplayModel: MutableLiveData<ChapterDisplayModel> = MutableLiveData()
    private val gson = GsonBuilder()
        .disableHtmlEscaping().create()

    //data
    private val data: MutableList<ChapterDisplayModel> = mutableListOf()


    fun loadAllInfo(l: String) {
        indexLink = l
        viewModelScope.launch(Dispatchers.IO) {
            data += NovelLinkConverter.getListOfChapter_mobile(indexLink)
            indexOrDetail post true
            println(gson.toJson(data))
            goToChapter(12)
        }

    }

    fun switch() {
        indexOrDetail post !indexOrDetail.value!!
    }

    /**
     * @param chapterNo start from 1
     */
    fun goToChapter(chapterNo: Int) {
        chapterDisplayModel post data[chapterNo - 1]
    }

    fun getToPrevChapter() {
        chapterDisplayModel post ChapterDisplayModel("", "")
    }

    fun getToNextChapter() {
        chapterDisplayModel post ChapterDisplayModel("", "")
    }


}