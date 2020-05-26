package com.hackday.imageSearch.ui.photo

import android.widget.ImageView
import androidx.paging.PagedList
import com.hackday.imageSearch.model.PhotoInfo

interface PhotoClickListener {
    fun photoClicked(
        image: ImageView,
        position: Int,
        clist: PagedList<PhotoInfo>?
    )
}