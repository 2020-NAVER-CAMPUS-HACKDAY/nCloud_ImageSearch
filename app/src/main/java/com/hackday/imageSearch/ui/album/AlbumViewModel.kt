package com.hackday.imageSearch.ui.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.hackday.imageSearch.database.model.PhotoTag
import com.hackday.imageSearch.repository.PhotoTagRepository

class AlbumViewModel(repository: PhotoTagRepository) : ViewModel() {

    val albumTitleList: LiveData<PagedList<PhotoTag>> =
        repository.getAllTag().toLiveData(pageSize = 50)

}
