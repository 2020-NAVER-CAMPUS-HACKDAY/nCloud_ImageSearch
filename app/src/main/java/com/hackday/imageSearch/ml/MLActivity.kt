package com.hackday.imageSearch.ml

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.MediaColumns
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class MLActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun getPathOfAllImages(): ArrayList<String>? {
        val result: ArrayList<String> = ArrayList()
        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection =
            arrayOf(MediaColumns.DATA, MediaColumns.DISPLAY_NAME)

        val cursor:Cursor? =
            contentResolver.query(uri, projection, null, null, MediaColumns.DATE_ADDED + " desc")

        val columnIndex: Int = cursor!!.getColumnIndexOrThrow(MediaColumns.DATA)
        val columnDisplayname: Int = cursor!!.getColumnIndexOrThrow(MediaColumns.DISPLAY_NAME)
        var lastIndex: Int
        while (cursor.moveToNext()) {
            val absolutePathOfImage: String = cursor!!.getString(columnIndex)
            val nameOfFile: String = cursor!!.getString(columnDisplayname)
            lastIndex = absolutePathOfImage.lastIndexOf(nameOfFile)
            lastIndex = if (lastIndex >= 0) lastIndex else nameOfFile.length - 1
            if (!TextUtils.isEmpty(absolutePathOfImage)) {
                result.add(absolutePathOfImage)
            }
        }
        for (string in result) {
            Log.i("PhotoSelectActivity.java | getPathOfAllImages", "|$string|")
        }
        return result
    }
}

