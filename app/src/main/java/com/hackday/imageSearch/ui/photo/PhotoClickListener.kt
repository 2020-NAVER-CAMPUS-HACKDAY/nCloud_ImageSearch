package com.hackday.imageSearch.ui.photo

import com.hackday.imageSearch.model.PhotoInfo

interface PhotoClickListener {
    fun photoClicked(
        photo: PhotoInfo?,
        pos: Int
    )
}