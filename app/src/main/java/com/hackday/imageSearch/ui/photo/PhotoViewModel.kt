package com.hackday.imageSearch.ui.photo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hackday.imageSearch.model.PhotoInfo
import com.hackday.imageSearch.repository.PhotoInfoRepositoryInjector.getPhotoRepositoryImpl
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver

class PhotoViewModel : ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()
    private val repository = getPhotoRepositoryImpl()

    private val _itemList = MutableLiveData<List<PhotoInfo>>(ArrayList())
    val itemList: LiveData<List<PhotoInfo>> get() = _itemList


    fun getAll() {
        disposable.add(
            repository.getAll()
                .subscribeWith(object : DisposableSingleObserver<List<PhotoInfo>>() {
                    override fun onSuccess(t: List<PhotoInfo>) {
                        _itemList.value = t
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }


    fun getPhotoById(id: String) {
        disposable.add(
            repository.getPhotoById(id)
                .subscribe()
        )
    }

    fun getPhotoByUri(uri: String) {
        disposable.add(
            repository.getPhotoByUri(uri)
                .subscribe()
        )
    }

    fun getPhotoByTag1(tag1: String) {
        disposable.add(
            repository.getPhotoByTag1(tag1)
                .subscribe()
        )
    }

    fun getPhotoByTag2(tag2: String) {
        disposable.add(
            repository.getPhotoByTag2(tag2)
                .subscribe()
        )
    }

    fun getPhotoByTag3(tag3: String) {
        disposable.add(
            repository.getPhotoByTag3(tag3)
                .subscribe()
        )
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}

