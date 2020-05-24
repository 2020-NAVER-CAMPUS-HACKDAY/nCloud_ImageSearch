package com.hackday.imageSearch.ml

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import androidx.work.*
import com.google.android.gms.tasks.Tasks
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.hackday.imageSearch.MyApplication
import com.hackday.imageSearch.database.PhotoInfoDatabase
import com.hackday.imageSearch.database.model.PhotoTag
import com.hackday.imageSearch.di.viewModelModule
import com.hackday.imageSearch.model.PhotoInfo
import com.hackday.imageSearch.repository.PhotoInfoRepositoryInjector
import com.hackday.imageSearch.ui.photoinfo.PhotoInfoViewModel
import java.io.IOException
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId


class MLLabelWorker(private val context: Context, private val workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    private var pathArrayList = ArrayList<Pair<String, String>>()

    private val photoInofoRepository = PhotoInfoRepositoryInjector.getPhotoRepositoryImpl()

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

    private fun createForegroundInfo(progress: String): ForegroundInfo {

        val channelId: String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel("channelId", "channelName")
        } else {
            ""
        }
        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setContentText(progress)
            .setSmallIcon(R.drawable.notification_icon_background)
            .build()

        return ForegroundInfo(1, notification)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String): String {
        val chan = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_NONE)

        val service: NotificationManager =
            getSystemService(context, NotificationManager::class.java) as NotificationManager
        service.createNotificationChannel(chan)

        return channelId
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getNoneLabeledList() {
        val idColumnName = MediaStore.Images.ImageColumns._ID
        val pathColumnName = MediaStore.Images.ImageColumns.DATA
        val dateColumnName = MediaStore.Images.ImageColumns.DATE_TAKEN

        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(idColumnName, pathColumnName, dateColumnName)

        context
            .contentResolver
            .query(
                uri,
                projection,
                MyApplication.prefs.getUrl,
                null,
                MediaStore.MediaColumns.DATE_ADDED + " desc"
            )
            ?.use {
                val idColumnIndex = it.getColumnIndexOrThrow(idColumnName)
                val dateColumnIndex = it.getColumnIndexOrThrow(dateColumnName)
                while (it.moveToNext()) {
                    val uri = ContentUris.withAppendedId(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        it.getLong(idColumnIndex)
                    ).toString()
                    val mills = it.getLong(dateColumnIndex)
                    val date = LocalDateTime.ofInstant(Instant.ofEpochMilli(mills), ZoneId.systemDefault()).toString().substring(0,10)
                    if (MyApplication.prefs.getUrl == null || MyApplication.prefs.getUrl.toString() < date) {
                        MyApplication.prefs.getUrl = date
                    }
                    PhotoInfoDatabase
                        .getInstance()
                        .photoInfoDao()
                        .getUriCountbyUri(uri)
                        .takeIf { count -> count == false }?.let {
                            val uriAndDate = Pair(uri, date)
                            pathArrayList.add(
                                uriAndDate
                            )
                        }
                }
            }
    }

    private fun addLabelToImageIfNeeded() {

        var howManyLabeled = 0
        lateinit var labelImage: FirebaseVisionImage
        for (uriAndDate in pathArrayList) {
            try {
                labelImage = FirebaseVisionImage.fromFilePath(context, Uri.parse(uriAndDate.first))
            } catch (e: IOException) {
                e.printStackTrace()
            }

            val detector = FirebaseVision.getInstance().getOnDeviceImageLabeler()

            val processTask = detector.processImage(labelImage)

            /** blocking! */
            val labels = Tasks.await(processTask); //프로세스 이미지를

            when (labels.size) {
                0 -> PhotoInfo(uriAndDate.second, uriAndDate.first, null, null, null)
                1 -> PhotoInfo(uriAndDate.second, uriAndDate.first, labels[0].text, null, null)
                2 -> PhotoInfo(
                    uriAndDate.second,
                    uriAndDate.first,
                    labels[0].text,
                    labels[1].text,
                    null
                )
                else -> PhotoInfo(
                    uriAndDate.second,
                    uriAndDate.first,
                    labels[0].text,
                    labels[1].text,
                    labels[2].text
                )
            }.let {
                photoInofoRepository.insertPhotoNonObserve(it)
            }

            for (label in labels){
                val photoTag = PhotoTag(label.text,uriAndDate.first)
                photoInofoRepository.insertTagNonObserve(photoTag)
            }

            reportProgress(++howManyLabeled,pathArrayList.size)
        }
    }

    private fun reportProgress(current: Int, total: Int) {
        val builder = NotificationCompat.Builder(applicationContext, "channelId")
            .setProgress(total, current, false)
            .setSmallIcon(androidx.work.R.drawable.notification_tile_bg)
        val service: NotificationManager =
            getSystemService(
                applicationContext,
                NotificationManager::class.java
            ) as NotificationManager
        service.notify(1, builder.build());
    }

}