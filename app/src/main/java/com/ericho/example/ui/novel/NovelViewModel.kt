package com.ericho.example.ui.novel

import androidx.lifecycle.MediatorLiveData
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
    val novel: MutableLiveData<NovelObject> = MutableLiveData()
    var currentSelectChapterIndex: MutableLiveData<Int> = MutableLiveData<Int>(0)
    private val gson = GsonBuilder()
        .disableHtmlEscaping().create()

    //data
    val chapterLiveData = MediatorLiveData<Chapter>()

    init {
        chapterLiveData.addSource(novel) {
            chapterLiveData.value = it.chapters[currentSelectChapterIndex.value!!]
        }
        chapterLiveData.addSource(currentSelectChapterIndex) {
            chapterLiveData.value = novel.value!!.chapters[it]
        }
    }

    fun loadAllInfo(l: String) {
        indexLink = l
        viewModelScope.launch(Dispatchers.IO) {
            currentSelectChapterIndex post 0
            novel.postValue(repository.getNovelData(l))
        }

    }

    fun switch() {
        indexOrDetail post !indexOrDetail.value!!
    }

    /**
     * @param chapterNo start from 1
     */
    fun goToChapter(chapterNo: Int = 1) {
    }

    fun getToPrevChapter() {
        chapterDisplayModel post Chapter("", "")
    }

    fun getToNextChapter() {
        chapterDisplayModel post Chapter("", "")
    }


}