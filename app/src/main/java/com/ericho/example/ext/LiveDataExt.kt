package com.ericho.example.ext

import androidx.lifecycle.MutableLiveData

infix fun <T> MutableLiveData<T>.post(value: T) {
    this.postValue(value)
}

infix fun <T> MutableLiveData<T>.postInMain(value: T) {
    this.value = value
}