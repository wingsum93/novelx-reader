package com.ericho.example.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ericho.example.ext.post

class MainViewModel : ViewModel() {

    val li_a = MutableLiveData<String>("Pending")


    fun start() {
        li_a post "abc"
    }
}