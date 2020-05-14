package com.hackday.imageSearch.ui.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hackday.imageSearch.R

class AlbumViewModel : ViewModel() {

    // sample data
    var sample = arrayListOf(
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background
    )

    private val _itemList = MutableLiveData<ArrayList<Int>>(sample)
    val itemList: LiveData<ArrayList<Int>> get() = _itemList
}