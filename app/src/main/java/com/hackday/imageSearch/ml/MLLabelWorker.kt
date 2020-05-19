package com.hackday.imageSearch.ml

import android.Manifest
import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentUris
import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationBuilderWithBuilderAccessor
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.room.Room
import androidx.work.*
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.hackday.imageSearch.database.PhotoInfoDatabase
import com.hackday.imageSearch.model.PhotoInfo
import java.io.IOException
import java.net.URI


class MLLabelWorker (private val context: Context, private val workerParams:WorkerParameters) : Worker(context,workerParams){

    private var pathArrayList = ArrayList< Pair<String,String> >()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun doWork(): Result {
        try {
            getNoneLabeledList()
            setForegroundAsync(createForegroundInfo("labeling...."))
            addLabelToImageIfNeeded()
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.failure()
        }

        return Result.success()
    }

    private fun createForegroundInfo(progress :String):ForegroundInfo{

        val channelId:String = if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            createNotificationChannel("channelId","channelName")
        }else
        {
            ""
        }
        val notification = NotificationCompat.Builder(applicationContext,channelId)
            .setContentText(progress)
            .build()

        return ForegroundInfo(1,notification)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String):String {
        val chan = NotificationChannel(channelId,channelName, NotificationManager.IMPORTANCE_NONE)

        val service :NotificationManager = getSystemService(context,NotificationManager::class.java) as NotificationManager
        service.createNotificationChannel(chan)

        return channelId
    }

    private fun getNoneLabeledList()
    {
        val idColumnName = MediaStore.Images.ImageColumns._ID
        val pathColumnName = MediaStore.Images.ImageColumns.DATA
        val dateColumnName = MediaStore.Images.ImageColumns.DATE_ADDED

        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(idColumnName, pathColumnName,dateColumnName)

        context
            .contentResolver
            .query(uri, projection, null, null, MediaStore.MediaColumns.DATE_ADDED + " desc")
            ?.use {
                val idColumnIndex = it.getColumnIndexOrThrow(idColumnName)
                val dateColumnIndex = it.getColumnIndexOrThrow(dateColumnName)
                while (it.moveToNext()) {
                    val uriAndDate = Pair(ContentUris.withAppendedId(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        it.getLong(idColumnIndex)).toString(),it.getLong(dateColumnIndex).toString())
                    pathArrayList.add(
                        uriAndDate
                    )
                }
            }
    }


    private fun addLabelToImageIfNeeded() {

        var howManyLabeled = 0
        lateinit var labelImage : FirebaseVisionImage
        for(uriAndDate in pathArrayList)
        {
            try {
                labelImage = FirebaseVisionImage.fromFilePath(context,Uri.parse(uriAndDate.first))
            }catch (e:IOException)
            {
                e.printStackTrace()
            }

            val detector = FirebaseVision.getInstance().getOnDeviceImageLabeler()
            detector.processImage(labelImage)
                .addOnSuccessListener { labels->
                    when (labels.size) {
                        1 -> PhotoInfo(uriAndDate.first, uriAndDate.second, labels[0].text, null,null)
                        2 -> PhotoInfo(uriAndDate.first, uriAndDate.second, labels[0].text, labels[1].text, null)
                        3 -> PhotoInfo(uriAndDate.first, uriAndDate.second, labels[0].text, labels[1].text, labels[2].text)
                        else -> PhotoInfo(uriAndDate.first, uriAndDate.second, null, null, null)
                    }.let {
                        PhotoInfoDatabase.getInstance().photoInfoDao().insertPhoto(it)
                    }
                    reportProgress(++howManyLabeled,pathArrayList.size)
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