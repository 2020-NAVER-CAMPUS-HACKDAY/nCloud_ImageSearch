package com.hackday.imageSearch.ml

import android.content.BroadcastReceiver
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.MediaColumns
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.hackday.imageSearch.R
import com.hackday.imageSearch.databinding.ActivitySplashBinding
import java.util.*


class MLActivity : AppCompatActivity(){

    private lateinit var viewModel:MLViewModel
    private lateinit var receiver:BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        initBinding(binding)

        startLabelWork()
    }

    private fun initBinding(binding:ActivitySplashBinding){
        viewModel = ViewModelProvider(this).get(MLViewModel::class.java)

        with(binding){
            vm=viewModel
            lifecycleOwner=this@MLActivity
        }
    }


    private fun startLabelWork() {
        val workRequest = createWorkRequest()

        startWorkRequest(workRequest)

        whenProgressIsUpdatedThenDoThis(workRequest.id) { newProgress ->
            viewModel.setProgress(newProgress)
        }
    }

    private fun createWorkRequest() = OneTimeWorkRequestBuilder<MLLabelWorker>().build()

    private fun startWorkRequest(workRequest: WorkRequest) = getWorkManager().enqueue(workRequest)

    private fun getWorkManager() = WorkManager.getInstance(this)

    private fun whenProgressIsUpdatedThenDoThis(workId: UUID, onUpdate: (Int) -> Any) {
        getWorkManager()
            .getWorkInfoByIdLiveData(workId)
            .observe(this, Observer { workInfo: WorkInfo? ->
                workInfo?.let {
                    onUpdate(it.progress.getInt("progress", 0))

                }
            })
    }



}

