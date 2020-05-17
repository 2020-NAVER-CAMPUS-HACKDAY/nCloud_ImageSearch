package com.hackday.imageSearch.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _isAlbumSelected = MutableLiveData<Boolean>(false)
    val isAlbumSelected: LiveData<Boolean> get() = _isAlbumSelected

    private val _albumPosition = MutableLiveData<Int>()
    val albumPosition: LiveData<Int> get() = _albumPosition

    private val _albumTagName = MutableLiveData<String>()
    val albumTagName: LiveData<String> get() = _albumTagName

    fun setFlag(flag: Boolean) {
        _isAlbumSelected.value = flag
    }

    fun setAlbumTagName(position: Int, label: String) {
        _albumPosition.value = position
        _albumTagName.value = label
    }
}
