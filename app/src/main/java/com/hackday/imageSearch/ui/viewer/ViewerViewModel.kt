package com.hackday.imageSearch.ui.viewer

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.hackday.imageSearch.model.PhotoInfo
import com.hackday.imageSearch.repository.PhotoInfoRepositoryImpl
import com.hackday.imageSearch.repository.PhotoInfoRepositoryInjector
import com.hackday.imageSearch.ui.photo.PhotoViewModel
import com.hackday.imageSearch.ui.photoinfo.PhotoInfoViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver

class ViewerViewModel : ViewModel() {

    var vdate : String = ""
    var vtag1 : String = ""
    var vtag2 : String = ""
    var vtag3 : String = ""

    val photoInfoRepository = PhotoInfoRepositoryInjector.getPhotoRepositoryImpl()

    fun getPhotoByUri(uri: String): LiveData<PhotoInfo>{
        return photoInfoRepository.getPhotoByUri(uri)
    }

}