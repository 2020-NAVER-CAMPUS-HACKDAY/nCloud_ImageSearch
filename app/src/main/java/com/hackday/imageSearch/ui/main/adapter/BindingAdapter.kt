package com.hackday.imageSearch.ui.main.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hackday.imageSearch.ui.album.TagModel
import com.hackday.imageSearch.ui.album.adapter.AlbumRecyclerAdapter

class BindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("setAlbumItem")
        fun setAlbumItem(rv: RecyclerView, itemList: List<TagModel>) {
            with(rv.adapter) {
                if (this is AlbumRecyclerAdapter) {
                    setItemList(itemList)
                }
            }

        }

    }
}