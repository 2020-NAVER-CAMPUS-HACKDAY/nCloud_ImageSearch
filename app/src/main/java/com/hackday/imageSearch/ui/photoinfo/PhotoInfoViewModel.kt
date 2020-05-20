package com.hackday.imageSearch.ui.photoinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.hackday.imageSearch.database.model.PhotoTag
import com.hackday.imageSearch.event.PhotoInfoEvent
import com.hackday.imageSearch.ml.MLLabelWorker
import com.hackday.imageSearch.model.PhotoInfo
import com.hackday.imageSearch.repository.PhotoInfoRepositoryImpl
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver


class PhotoInfoViewModel (
    private val photoInfoRepository: PhotoInfoRepositoryImpl
) : ViewModel() {

    val disposable: CompositeDisposable = CompositeDisposable()

    var photoOne : MutableLiveData<PhotoInfo> = MutableLiveData()
    val photoInfoList = MutableLiveData<ArrayList<PhotoInfo>>().apply {
        value = ArrayList()
    }
//    fun getAll(){
//        disposable.add(
//            photoInfoRepository.getAll()
//                .subscribe()
//        )
//    }

    fun deleteAll(){
        disposable.add(
            photoInfoRepository.deleteAll()
                .subscribe()
        )
    }

    fun delete(photoInfo: PhotoInfo){
        disposable.add(
            photoInfoRepository.delete(photoInfo)
                .subscribe()
        )
    }

    fun getPhotoByUri(uri: String): LiveData<PhotoInfo> {
        return photoInfoRepository.getPhotoByUri(uri)
    }

    fun getPhotoByTag1(tag1: String){
        disposable.add(
            photoInfoRepository.getPhotoByTag1(tag1)
                .subscribe()
        )
    }

    fun getPhotoByTag2(tag2: String){
        disposable.add(
            photoInfoRepository.getPhotoByTag2(tag2)
                .subscribe()
        )
    }

    fun getPhotoByTag3(tag3: String){
        disposable.add(
            photoInfoRepository.getPhotoByTag3(tag3)
                .subscribe()
        )
    }

    fun insertPhoto(photoInfo: PhotoInfo){
        disposable.add(
            photoInfoRepository.insertPhoto(photoInfo)
                .subscribe()
        )
    }

    fun insertPhotoList(photoInfoList: ArrayList<PhotoInfo>){
        disposable.add(
            photoInfoRepository.insertPhotoList(photoInfoList)
                .subscribe()
        )
    }

    fun getUriCountbyUri(uri: String): Boolean{
        return photoInfoRepository.getUriCountbyUri(uri)
    }

    fun insertTag(photoTag: PhotoTag){
        disposable.add(
            photoInfoRepository.insertTag(photoTag)
                .subscribe()
        )
    }

    fun getAlltag(){
        disposable.add(
            photoInfoRepository.getAllTag()
                .subscribe()
        )
    }

    fun deleteAllTag(){
        disposable.add(
            photoInfoRepository.deleteAllTag()
                .subscribe()
        )
    }

    fun deleteTag(photoTag: PhotoTag){
        disposable.add(
            photoInfoRepository.deleteTag(photoTag)
                .subscribe()
        )
    }

    fun subscribePhotoInfoEvent(){
        disposable.add(
            PhotoInfoEvent.addPhotoInfoListSubject.subscribe(){
                photoInfoList.value?.add(it)
                photoInfoList.postValue(photoInfoList.value)
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}