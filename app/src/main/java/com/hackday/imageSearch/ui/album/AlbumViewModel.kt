package com.hackday.imageSearch.ui.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AlbumViewModel : ViewModel() {

    // 임시
    private val default = listOf<TagModel>(
        TagModel("image", "장소"),
        TagModel("image", "동물")
    )

    private val _itemList = MutableLiveData<List<TagModel>>(default)
    val itemList: LiveData<List<TagModel>> get() = _itemList
}
