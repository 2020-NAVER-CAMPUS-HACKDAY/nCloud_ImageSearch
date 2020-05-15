package com.hackday.imageSearch.ui.viewer

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hackday.imageSearch.model.PhotoInfo
import com.hackday.imageSearch.repository.PhotoInfoRepositoryImpl
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver

class ViewerViewModel : ViewModel() {

    private var repository: PhotoInfoRepositoryImpl = PhotoInfoRepositoryImpl()

    var photoOne : MutableLiveData<PhotoInfo> = MutableLiveData()

    fun getPhotoByUri(uri: String){
        CompositeDisposable().add(repository.getPhotoByUri(uri)
            .subscribeWith(object : DisposableSingleObserver<PhotoInfo>(){
                override fun onSuccess(t: PhotoInfo) {
                    photoOne.value = t
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            }))
    }

    fun getPhotoById(id: String) = repository.getPhotoById(id)

}