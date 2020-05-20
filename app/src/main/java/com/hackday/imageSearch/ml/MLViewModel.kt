package com.hackday.imageSearch.ml

import android.view.View
import androidx.lifecycle.*
import com.hackday.imageSearch.database.model.PhotoTag
import com.hackday.imageSearch.event.PhotoInfoEvent
import com.hackday.imageSearch.event.PhotoTagEvent
import com.hackday.imageSearch.model.PhotoInfo
import com.hackday.imageSearch.repository.PhotoInfoRepositoryInjector
import io.reactivex.disposables.CompositeDisposable

class MLViewModel:ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()
    private val photoInfoRepository = PhotoInfoRepositoryInjector.getPhotoRepositoryImpl()

    val photoInfoList = MutableLiveData<ArrayList<PhotoInfo>>().apply {
        value = ArrayList()
    }
    val photoTagList = MutableLiveData<ArrayList<PhotoTag>>().apply {
        value = ArrayList()
    }

    private val _current = MutableLiveData<Int>()
    val current:LiveData<Int> =_current

    private val _total = MutableLiveData<Int>()
    val total:LiveData<Int> = _total
    fun setCurrent(current:Int)
    {
        _current.postValue(current)
    }
    fun setTotal(total:Int)
    {
        _total.postValue(total)
    }

    fun insertPhotoInfoList(photoInfoList: ArrayList<PhotoInfo>){
        disposable.add(
            photoInfoRepository.insertPhotoList(photoInfoList)
                .subscribe()
        )
    }

    fun insertPhotoTagList(photoTagList: ArrayList<PhotoTag>){
        disposable.add(
            photoInfoRepository.insertTagList(photoTagList)
                .subscribe()
        )
    }

    fun subscribePhotoInfoEvent(){
        disposable.add(
            PhotoInfoEvent.addPhotoInfoListSubject.subscribe{
                photoInfoList.value?.add(it)
                photoInfoList.postValue(photoInfoList.value)
            }
        )
    }

    fun subscribePhotoTagEvent(){
        disposable.add(
            PhotoTagEvent.addPhotoTagListSubject.subscribe{
                photoTagList.value?.add(it)
                photoTagList.postValue(photoTagList.value)
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}

