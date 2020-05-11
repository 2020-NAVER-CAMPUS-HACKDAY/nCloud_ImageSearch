package com.hackday.imageSearch

import android.app.Application
import com.hackday.imageSearch.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(viewModelModule)
        }
    }

}