package com.hackday.imageSearch.ml

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.hackday.imageSearch.R
import com.hackday.imageSearch.databinding.ActivitySplashBinding
import com.hackday.imageSearch.ui.main.MainActivity
import java.util.*


class MLActivity : AppCompatActivity() {

    private lateinit var viewModel: MLViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySplashBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_splash)
        initBinding(binding)
        getPermission()
    }

    private fun initBinding(binding: ActivitySplashBinding) {
        viewModel = ViewModelProvider(this).get(MLViewModel::class.java)

        with(binding) {
            vm = viewModel
            lifecycleOwner = this@MLActivity
        }
    }

    private fun getPermission() {
        var permissionListener: PermissionListener = object : PermissionListener {

            override fun onPermissionGranted() {
                startLabelWork()
                val nextIntent = Intent(applicationContext, MainActivity::class.java)
                startActivity(nextIntent)
                finish()
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {

            }
        }

        TedPermission.with(applicationContext).setPermissionListener(permissionListener)
            .setDeniedMessage("권한이 거부되었습니다. 사용을 원하시면 설정에서 해당 권한을 직접 허용해주세요.")
            .setPermissions(Manifest.permission.CAMERA)
            .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
            .check()
    }

    private fun startLabelWork() {
        val workRequest = createWorkRequest()

        startWorkRequest(workRequest)

        whenProgressIsUpdatedThenDoThis(workRequest.id) { current, total ->
            viewModel.setCurrent(current)
            viewModel.setTotal(total)
        }
        whenProgressIsUpdatedThenDoThis(workRequest.id)
    }

    private fun createWorkRequest() = OneTimeWorkRequestBuilder<MLLabelWorker>().build()

    private fun startWorkRequest(workRequest: WorkRequest) = getWorkManager().enqueue(workRequest)

    private fun getWorkManager() = WorkManager.getInstance(this)

    private fun whenProgressIsUpdatedThenDoThis(workId: UUID, onUpdate: (Int, Int) -> Unit) {
        getWorkManager()
            .getWorkInfoByIdLiveData(workId)
            .observe(this, Observer { workInfo: WorkInfo? ->
                workInfo?.let {
                    onUpdate(it.progress.getInt("current", 0), it.progress.getInt("total", 0))
                }
            })
    }

    private fun whenProgressIsUpdatedThenDoThis(workId: UUID) {
        getWorkManager()
            .getWorkInfoByIdLiveData(workId)
            .observe(this, Observer { workInfo: WorkInfo? ->
                workInfo?.let {
                    if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                        val nextIntent = Intent(this, MainActivity::class.java)
                        startActivity(nextIntent)
                        finish()
                    }
                }
            })
    }

}

