package com.ericho.example

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Android context
            androidContext(this@App)
            // your modules
            modules(appModule)

        }
        Timber.plant(Timber.DebugTree())
    }

    override fun onTerminate() {
        stopKoin()
        super.onTerminate()
    }
}