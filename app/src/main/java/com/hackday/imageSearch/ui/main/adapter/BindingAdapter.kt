package com.hackday.imageSearch.ui.main.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hackday.imageSearch.database.model.PhotoTag
import com.hackday.imageSearch.ui.album.adapter.AlbumRecyclerAdapter

class BindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("setAlbumItem")
        fun setAlbumItem(rv: RecyclerView, itemList: List<PhotoTag>) {
            with(rv.adapter) {
                if (this is AlbumRecyclerAdapter) {
                    setItemList(itemList)
                }
            }

        }

    }
}