package com.hackday.imageSearch.ui.photo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.hackday.imageSearch.model.PhotoInfo
import com.hackday.imageSearch.repository.PhotoInfoRepository

class PhotoViewModel(repository: PhotoInfoRepository) : ViewModel() {

    private val repo = repository
    lateinit var albumItemList: LiveData<PagedList<PhotoInfo>>

    val itemList: LiveData<PagedList<PhotoInfo>> = repository.getAll().toLiveData(pageSize = 50)

    fun setAlbumItemList(tag: String) {
        albumItemList = repo.getAllPhotoByTag(tag).toLiveData(pageSize = 50)
    }

}

