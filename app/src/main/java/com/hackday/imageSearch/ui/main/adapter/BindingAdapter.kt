package com.hackday.imageSearch.ui.main.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hackday.imageSearch.model.PhotoInfo
import com.hackday.imageSearch.ui.album.adapter.AlbumRecyclerAdapter
import com.hackday.imageSearch.ui.photo.adapter.PhotoRecyclerAdapter

class BindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("setItem")
        fun setItem(rv: RecyclerView, itemList: List<PhotoInfo>) {
            with(rv.adapter) {
                if (this is AlbumRecyclerAdapter) {
                    //setItemList(itemList)
                } else if (this is PhotoRecyclerAdapter) {
                    setItemList(itemList)
                }
            }

        }
    }
}