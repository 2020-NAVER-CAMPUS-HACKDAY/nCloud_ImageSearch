package com.hackday.imageSearch.ml

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.hackday.imageSearch.ui.main.MainActivity


class MLActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPermission()
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
        if (!isWorkerRunning())
            startWorkRequest(workRequest)
    }

    private fun createWorkRequest() = OneTimeWorkRequestBuilder<MLLabelWorker>()
        .addTag("initWork")
        .build()

    private fun startWorkRequest(workRequest: WorkRequest) = getWorkManager().enqueue(workRequest)

    private fun getWorkManager() = WorkManager.getInstance(this)

    private fun isWorkerRunning() = getWorkManager().getWorkInfosByTag("initWork").get()
        .indexOfFirst { !it.state.isFinished } >= 0

}


