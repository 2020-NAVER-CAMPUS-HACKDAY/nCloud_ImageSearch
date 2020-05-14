package com.hackday.imageSearch.ui.viewer

import android.content.Intent
import androidx.lifecycle.ViewModel
import com.hackday.imageSearch.repository.PhotoInfoRepositoryImpl

class ViewerViewModel : ViewModel() {

    private var repository: PhotoInfoRepositoryImpl = PhotoInfoRepositoryImpl()
    var id_ : String = ""
    var uri_ : String = ""

    fun getPhotoByUri(uri: String) = repository.getPhotoByUri(uri)

    fun getPhotoById(id: String) = repository.getPhotoById(id)

//    fun onClickInfo(){
//
//    }
}