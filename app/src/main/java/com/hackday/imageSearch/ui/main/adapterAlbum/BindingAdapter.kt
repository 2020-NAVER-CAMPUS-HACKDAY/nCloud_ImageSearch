package com.hackday.imageSearch.ui.main.adapterAlbum

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

class BindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("setItem")
        fun setItem(rv: RecyclerView, itemList: ArrayList<Int>) {
            with(rv.adapter) {
                if (this is AlbumRecyclerAdapter) {
                    setItemList(itemList)
                }
            }

        }
    }
}