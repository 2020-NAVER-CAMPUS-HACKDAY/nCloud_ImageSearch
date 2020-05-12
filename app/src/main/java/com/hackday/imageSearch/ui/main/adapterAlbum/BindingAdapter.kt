package com.hackday.imageSearch.ui.main.adapterAlbum

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hackday.imageSearch.ui.main.adapterPhoto.PhotoRecyclerAdapter

class BindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("setItem")
        fun setItem(rv: RecyclerView, itemList: ArrayList<Int>) {
            with(rv.adapter) {
                if (this is AlbumRecyclerAdapter) {
                    setItemList(itemList)
                }
                else if (this is PhotoRecyclerAdapter){
                    setItemList(itemList)
                }
            }

        }
    }
}