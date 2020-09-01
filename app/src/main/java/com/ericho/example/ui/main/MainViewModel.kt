package com.ericho.example.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ericho.example.ext.post

class MainViewModel : ViewModel() {

    val title = MutableLiveData<String>("aaaa")
    val novelTitle = MutableLiveData<String>("aaaa")

    fun start() {
        title post "Novel Repo"
        novelTitle post "Novel A"
    }
}