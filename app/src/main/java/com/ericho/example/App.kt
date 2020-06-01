package com.ericho.example

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Android context
            androidContext(this@App)
            // your modules
            modules(appModule)
        }
    }
}