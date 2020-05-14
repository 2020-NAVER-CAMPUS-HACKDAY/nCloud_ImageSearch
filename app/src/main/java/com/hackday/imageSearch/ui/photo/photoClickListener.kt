package com.hackday.imageSearch.ui.photo

import android.net.Uri
import com.hackday.imageSearch.model.PhotoInfo

interface photoClickListener {
    fun photoClicked(
        photo: PhotoInfo
    )
}