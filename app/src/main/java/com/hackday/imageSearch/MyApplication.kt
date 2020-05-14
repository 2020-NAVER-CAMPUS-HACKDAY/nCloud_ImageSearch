package com.hackday.imageSearch

import android.app.Application
import android.content.Context
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

    init{
        instance = this
    }

    companion object {
        private var instance: MyApplication? = null
        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

}