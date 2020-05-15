package com.hackday.imageSearch.ml

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class MLSomeWorking {
    fun doYourJob(context : Context)
    {
        val MLWorkRquest = OneTimeWorkRequestBuilder<MLWorker>().build()
        WorkManager.getInstance(context).enqueue(MLWorkRquest)
    }
}