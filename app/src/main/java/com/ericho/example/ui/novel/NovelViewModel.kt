package com.ericho.example.ui.novel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ericho.example.ext.post

class NovelViewModel : ViewModel() {

    val li_a = MutableLiveData<String>("Pending")


    fun start() {
        li_a post "abc"
    }
}