package com.hackday.imageSearch.ml

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.room.Room
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.hackday.imageSearch.database.PhotoInfoDatabase
import java.io.IOException


class MLLabelWorker (private val context: Context, private val workerParams:WorkerParameters) : Worker(context,workerParams){

    private var pathArrayList = ArrayList<Uri>()


    override fun doWork(): Result {
        try {
            getPermission()
            addLabelToImageIfNeeded {path, current ->
                reportProgress(current)

            }
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.failure()
        }

        return Result.success()
    }

    private fun getNoneLabeledList()
    {


        val idColumnName = MediaStore.Images.ImageColumns._ID
        val pathColumnName = MediaStore.Images.ImageColumns.DATA

        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(idColumnName, pathColumnName)

        val cursor = context
            .contentResolver
            .query(uri, projection, null, null, MediaStore.MediaColumns.DATE_ADDED + " desc")
            ?: throw RuntimeException("This should not happen!")

        val idColumnIndex = cursor.getColumnIndexOrThrow(idColumnName)
        val pathColumnIndex: Int = cursor.getColumnIndexOrThrow(pathColumnName)

        val numberOfImages = cursor.count
        var currentRowIndex = 0

        while (cursor.moveToNext()) {
            val id = cursor.getString(idColumnIndex)
            val path = cursor.getString(pathColumnIndex)
            pathArrayList.add(path)
        }

        cursor.close();
    }


    private fun addLabelToImageIfNeeded(doThis: (String,  Int) -> Any ) {

        getPermission()
        lateinit var labelImage : FirebaseVisionImage
        for(i in pathArrayList)
        {
            try {
                //TODO : URI를 이용해 firepath를 찾아야하는데..
                labelImage = FirebaseVisionImage.fromFilePath(context,pathArrayList[i])
            }catch (e:IOException)
            {
                e.printStackTrace()
            }

            val detector = FirebaseVision.getInstance().getCloudImageLabeler()
            detector.processImage(labelImage)
                .addOnSuccessListener { labels->
                    //TODO DB에 삽입

                }



        }
    }

    private fun reportProgress(progress:Int) {
        // setProgressAsync() -> MLActivity에 observe에 걸린다.
        val newData = Data.Builder().putInt("progress",progress).build()
        setProgressAsync(newData)
    }

    private fun getPermission()
    {
        var permissionListener: PermissionListener = object:PermissionListener {

            override fun onPermissionGranted() {

            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {

            }
        }

        TedPermission.with(applicationContext) .setPermissionListener(permissionListener)
            .setDeniedMessage("권한이 거부되었습니다. 사용을 원하시면 설정에서 해당 권한을 직접 허용해주세요.")
            .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE) .check();
    }
}