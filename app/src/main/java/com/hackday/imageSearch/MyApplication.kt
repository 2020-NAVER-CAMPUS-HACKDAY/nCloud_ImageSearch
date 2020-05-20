package com.hackday.imageSearch

import android.app.Application
import android.content.Context
import com.hackday.imageSearch.di.appModule
import com.hackday.imageSearch.di.viewModelModule
import com.hackday.imageSearch.ml.MLManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        prefs = MLManager(applicationContext)
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
            modules(viewModelModule)
        }
    }

    init {
        instance = this
    }

    companion object {

        lateinit var prefs: MLManager
        private var instance: MyApplication? = null
        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

}