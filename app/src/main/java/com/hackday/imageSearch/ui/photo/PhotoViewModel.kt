package com.hackday.imageSearch.ui.photo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.hackday.imageSearch.model.PhotoInfo
import com.hackday.imageSearch.repository.PhotoInfoRepository

class PhotoViewModel(repository: PhotoInfoRepository) : ViewModel() {

    val itemList: LiveData<PagedList<PhotoInfo>> = repository.getAll().toLiveData(pageSize = 50)
}

