package com.example.beelditest

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class BeeldiApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initLogger()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@BeeldiApplication)
            modules(di)
        }
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}