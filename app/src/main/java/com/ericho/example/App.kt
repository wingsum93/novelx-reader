package com.ericho.example

import android.app.Application
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
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

        getKoin().createScope("l", named("local"))
        getKoin().createScope("r", named("remote"))
        Timber.plant(Timber.DebugTree())
    }
}