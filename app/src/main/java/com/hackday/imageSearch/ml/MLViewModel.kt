package com.hackday.imageSearch.ml

import android.view.View
import androidx.lifecycle.*
import com.hackday.imageSearch.database.model.PhotoTag
import com.hackday.imageSearch.event.PhotoInfoEvent
import com.hackday.imageSearch.event.PhotoTagEvent
import com.hackday.imageSearch.model.PhotoInfo
import com.hackday.imageSearch.repository.PhotoInfoRepositoryInjector
import io.reactivex.disposables.CompositeDisposable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MLViewModel : ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()
    private val photoInfoRepository = PhotoInfoRepositoryInjector.getPhotoRepositoryImpl()

    private val _current = MutableLiveData<Int>()
    val current: LiveData<Int> = _current

    private val _total = MutableLiveData<Int>()
    val total:LiveData<Int> = _total

    init{
        subscribePhotoInfoEvent()
        subscribePhotoTagEvent()
    }

    fun setCurrent(current: Int) {
        _current.postValue(current)
    }

    fun setTotal(total: Int) {
        _total.postValue(total)
    }

    fun insertPhotoInfo(photoInfo: PhotoInfo){
        disposable.add(
            photoInfoRepository.insertPhoto(photoInfo)
                .subscribe()
        )
    }

    fun insertPhotoTag(photoTag: PhotoTag){
        disposable.add(
            photoInfoRepository.insertTag(photoTag)
                .subscribe()
        )
    }

    fun subscribePhotoInfoEvent(){
        disposable.add(
            PhotoInfoEvent.addPhotoInfoListSubject.subscribe{
                insertPhotoInfo(it)
            }
        )
    }

    fun subscribePhotoTagEvent(){
        disposable.add(
            PhotoTagEvent.addPhotoTagListSubject.subscribe{
                insertPhotoTag(it)
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}

