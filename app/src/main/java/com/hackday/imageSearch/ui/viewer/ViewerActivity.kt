package com.hackday.imageSearch.ui.viewer

import android.net.Uri
import android.os.Bundle
import com.hackday.imageSearch.R
import com.hackday.imageSearch._base.BaseActivity
import com.hackday.imageSearch.databinding.ActivityViewerBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ViewerActivity : BaseActivity<ActivityViewerBinding>() {
    override val vm: ViewerViewModel by viewModel()
    override fun getLayoutRes() = R.layout.activity_viewer

    override fun setupBinding() {
        binding.vm = vm
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun updateInfo(id: String, uri: String){
        // id와 uri 를 통해 db에서 사진의 디테일 정보 불러오기
    }

    companion object{
        const val EXTRA_PHOTO_ID = "EXTRA_PHOTO_ID"
        const val EXTRA_PHOTO_DATE = "EXTRA_PHOTO_DATE"
        const val EXTRA_PHOTO_GPS = "EXTRA_PHOTO_GPS"
        const val EXTRA_PHOTO_URI = "EXTRA_PHOTO_URI"
        const val EXTRA_PHOTO_TAG1 = "EXTRA_PHOTO_TAG1"
        const val EXTRA_PHOTO_TAG2 = "EXTRA_PHOTO_TAG2"
        const val EXTRA_PHOTO_TAG3 = "EXTRA_PHOTO_TAG3"
    }
}

