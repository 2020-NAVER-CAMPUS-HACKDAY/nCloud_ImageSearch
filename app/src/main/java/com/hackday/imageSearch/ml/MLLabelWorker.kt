package com.hackday.imageSearch.ml

import android.Manifest
import android.annotation.SuppressLint
import android.app.Notification
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.room.Room
import androidx.work.*
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.hackday.imageSearch.database.PhotoInfoDatabase
import java.io.IOException
import java.net.URI


class MLLabelWorker (private val context: Context, private val workerParams:WorkerParameters) : Worker(context,workerParams){

    private var pathArrayList = ArrayList<String>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun doWork(): Result {
        try {
            getNoneLabeledList()
            setForegroundAsync(createForegroundInfo("labeling...."))
            addLabelToImageIfNeeded {current,total ->
                reportProgress(current,total)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.failure()
        }

        return Result.success()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createForegroundInfo(progress :String):ForegroundInfo{
        val notification = Notification.Builder(applicationContext,"${context.packageName}")
            .setContentText(progress)
            .build()

        return ForegroundInfo(1,notification)

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

        while (cursor.moveToNext()) {
            val path = cursor.getString(pathColumnIndex)
            pathArrayList.add(path)
        }

        cursor.close();
    }


    private fun addLabelToImageIfNeeded(doThis: (Int,Int) -> Any ) {

        var howManyLabeled = 0
        lateinit var labelImage : FirebaseVisionImage
        for(uri in pathArrayList)
        {
            try {
                labelImage = FirebaseVisionImage.fromFilePath(context,Uri.parse(uri))
            }catch (e:IOException)
            {
                e.printStackTrace()
            }

            val detector = FirebaseVision.getInstance().getCloudImageLabeler()
            detector.processImage(labelImage)
                .addOnSuccessListener { labels->
                    //TODO : DB에 삽입
                    doThis(++howManyLabeled,pathArrayList.size)

                }
        }
    }

    private fun reportProgress(current:Int, total:Int) {
        val newData = Data.Builder()
            .putInt("current",current)
            .putInt("total",total)
            .build()
        setProgressAsync(newData)
    }

}