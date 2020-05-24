package com.hackday.imageSearch.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hackday.imageSearch.model.PhotoInfo
import com.hackday.imageSearch.repository.PhotoInfoRepositoryInjector
import io.reactivex.disposables.CompositeDisposable

class MainViewModel : ViewModel() {

    private val _isAlbumSelected = MutableLiveData<Boolean>(false)
    val isAlbumSelected: LiveData<Boolean> get() = _isAlbumSelected

    private val _albumPosition = MutableLiveData<Int>()
    val albumPosition: LiveData<Int> get() = _albumPosition

    private val _albumTagName = MutableLiveData<String>()
    val albumTagName: LiveData<String> get() = _albumTagName

    private val _back = MutableLiveData<Boolean>(false)
    val back: LiveData<Boolean> get() = _back

    fun setIsAlbumSelected(flag: Boolean) {
        _isAlbumSelected.value = flag
    }

    fun setAlbumTagName(position: Int, label: String?) {
        _albumPosition.value = position
        _albumTagName.value = label
    }

    fun setBack() {
        _back.value = true
    }

    // test
    private val disposable: CompositeDisposable = CompositeDisposable()
    private val repository = PhotoInfoRepositoryInjector.getPhotoRepositoryImpl()

    var i = 0

    fun test() {
        val sample = PhotoInfo(i++.toString(), i++.toString(), "1", "1", "1")
        disposable.add(
            repository.insertPhoto(sample)
                .subscribe()
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}
