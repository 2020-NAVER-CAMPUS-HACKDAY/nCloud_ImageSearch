package com.hackday.imageSearch.ml

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters


class MLLabelWorker (private val context: Context, private val workerParams:WorkerParameters) : Worker(context,workerParams){

    private var pathArrayList = ArrayList<String>()
    override fun doWork(): Result {
        try {
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

        for(i in pathArrayList)
        {
            //TODO : MLkit로 라벨링 하면서 DB에 삽입하기
        }
    }

    private fun reportProgress(progress:Int) {
        // setProgressAsync() -> MLActivity에 observe에 걸린다.
        val newData = Data.Builder().putInt("progress",progress).build()
        setProgressAsync(newData)
    }
}