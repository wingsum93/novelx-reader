package com.ericho.example.ui.novel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ericho.example.data.INovelRepository
import com.ericho.example.ext.post
import com.ericho.example.ui.novel.chapter.Chapter
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NovelViewModel(
    private val repository: INovelRepository
) : ViewModel() {
    var indexLink: String = ""
        private set
    val indexOrDetail = MutableLiveData(true)
    var chapterDisplayModel: MutableLiveData<Chapter> = MutableLiveData()
    val chapters = MutableLiveData<List<Chapter>>()
    val novel: MutableLiveData<NovelObject> = MutableLiveData()
    var currentSelectChapter: Chapter? = null
    private val gson = GsonBuilder()
        .disableHtmlEscaping().create()

    //data
    private val data: MutableList<Chapter> = mutableListOf()


    fun loadAllInfo(l: String) {
        indexLink = l
        viewModelScope.launch(Dispatchers.IO) {
            novel.postValue(repository.getNovelData(l))
            indexOrDetail post true
        }

    }

    fun switch() {
        indexOrDetail post !indexOrDetail.value!!
    }

    /**
     * @param chapterNo start from 1
     */
    fun goToChapter(chapterNo: Int = 1) {
        chapterDisplayModel post data[chapterNo - 1]
    }

    fun getToPrevChapter() {
        chapterDisplayModel post Chapter("", "")
    }

    fun getToNextChapter() {
        chapterDisplayModel post Chapter("", "")
    }


}