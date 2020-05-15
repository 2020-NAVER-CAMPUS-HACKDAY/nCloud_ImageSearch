package com.hackday.imageSearch.ml

import android.app.Activity
import android.content.BroadcastReceiver
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.MediaColumns
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.hackday.imageSearch.R
import com.hackday.imageSearch.databinding.ActivityMainBinding
import com.hackday.imageSearch.databinding.ActivitySplashBinding
import com.hackday.imageSearch.ui.main.MainViewModel


class MLActivity : AppCompatActivity(){

    private lateinit var viewModel:MLViewModel
    private lateinit var receiver:BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        initBinding(binding)
    }

    private fun initBinding(binding:ActivitySplashBinding){
        viewModel = ViewModelProvider(this).get(MLViewModel::class.java)

        with(binding){

            vm=viewModel
            lifecycleOwner=this@MLActivity
        }
    }



    private fun getPathOfAllImages(): ArrayList<String>? {
        val result: ArrayList<String> = ArrayList()
        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection =
            arrayOf(MediaColumns.DATA, MediaColumns.DISPLAY_NAME)

        val cursor =
            contentResolver.query(uri, projection, null, null, MediaColumns.DATE_ADDED + " desc")

        val columnIndex: Int = cursor?.getColumnIndexOrThrow(MediaColumns.DATA) ?: return result
        while (cursor.moveToNext()) {
            val absolutePathOfImage: String = cursor.getString(columnIndex)
            if (!TextUtils.isEmpty(absolutePathOfImage)) {
                result.add(absolutePathOfImage)
            }
        }
        for (string in result) {
            Log.i("PhotoSelectActivity.java | getPathOfAllImages", "|$string|")
        }
        cursor.close();
        return result
    }
}

