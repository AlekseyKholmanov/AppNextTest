package com.example.appnext

import android.app.Application
import com.example.appnext.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        buildKoin()
    }

    private fun buildKoin(){
        startKoin {
            androidContext(this@App)
            workManagerFactory()
            modules(appComponent)
        }
    }
}