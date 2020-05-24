package com.hackday.imageSearch.ml

import android.Manifest
import android.app.NotificationManager
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.hackday.imageSearch.R
import com.hackday.imageSearch.databinding.ActivitySplashBinding
import com.hackday.imageSearch.ui.main.MainActivity


class MLActivity : AppCompatActivity() {

    private lateinit var viewModel: MLViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySplashBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_splash)

        getPermission()
    }


    private fun getPermission() {
        var permissionListener: PermissionListener = object : PermissionListener {

            val service: NotificationManager =
                ContextCompat.getSystemService(
                    applicationContext,
                    NotificationManager::class.java
                ) as NotificationManager

            var notification = service.activeNotifications

            override fun onPermissionGranted() {
                if (notification.size == 0) {
                    startLabelWork()
                }
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
    }

    private fun createWorkRequest() = OneTimeWorkRequestBuilder<MLLabelWorker>().build()

    private fun startWorkRequest(workRequest: WorkRequest) = getWorkManager().enqueue(workRequest)

    private fun getWorkManager() = WorkManager.getInstance(this)

}

