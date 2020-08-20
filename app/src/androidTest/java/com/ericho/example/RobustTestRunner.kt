package com.ericho.example

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class EricRunner : AndroidJUnitRunner() {

    override fun newApplication(
        classLoader: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(classLoader, TestApp::class.java.name, context)
    }
}