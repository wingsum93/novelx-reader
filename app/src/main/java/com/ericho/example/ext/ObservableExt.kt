package com.ericho.example.ext

import androidx.databinding.ObservableBoolean

infix fun ObservableBoolean.post(value: Boolean) {
    this.set(value)
}

infix fun ObservableBoolean.postInMain(value: Boolean) {
    this.set(value)
}