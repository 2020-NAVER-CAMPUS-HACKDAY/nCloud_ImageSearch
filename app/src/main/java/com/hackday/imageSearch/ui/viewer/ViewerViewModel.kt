package com.hackday.imageSearch.ui.viewer

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.hackday.imageSearch.model.PhotoInfo
import com.hackday.imageSearch.repository.PhotoInfoRepositoryImpl
import com.hackday.imageSearch.ui.photo.PhotoViewModel
import com.hackday.imageSearch.ui.photoinfo.PhotoInfoViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver

class ViewerViewModel : ViewModel() {

    var repository: PhotoInfoRepositoryImpl = PhotoInfoRepositoryImpl()

//    private var pvm = PhotoInfoViewModel(repository)
//
//    fun getPhotoByUri(uri: String){
//        pvm.getPhotoByUri(uri)
//    }
//
//    fun getPhotoById(id: String){
//
//    }

}