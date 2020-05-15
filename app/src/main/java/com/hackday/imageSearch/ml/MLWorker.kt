package com.hackday.imageSearch.ml

import android.content.Context
import android.content.Intent
import androidx.work.Worker
import androidx.work.WorkerParameters

class MLWorker (private val context: Context,workerParams:WorkerParameters) : Worker(context,workerParams){
    override fun doWork(): Result {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //브로드캐스트로 인텐트 뿌리기
    private fun reportProgress(progress:Int)
    {
        val intent = Intent("GALLERY_PROGRESS_REPORT").apply {
            putExtra("PROGRESS",progress)
        }
        context.sendBroadcast(intent)
    }
}