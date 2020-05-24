package com.hackday.imageSearch.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hackday.imageSearch.model.PhotoInfo
import com.hackday.imageSearch.repository.PhotoInfoRepositoryInjector
import io.reactivex.disposables.CompositeDisposable

class MainViewModel : ViewModel() {

    private val _replaceFragment = MutableLiveData<Boolean>(false)
    val replaceFragment: LiveData<Boolean> get() = _replaceFragment

    private val _albumPosition = MutableLiveData<Int>()
    val albumPosition: LiveData<Int> get() = _albumPosition

    private val _albumTagName = MutableLiveData<String>("")
    val albumTagName: LiveData<String> get() = _albumTagName

    private val _albumSearchTagName = MutableLiveData<String>("")
    val searchTagName: LiveData<String> get() = _albumSearchTagName

    private val _isBtnSearchClicked = MutableLiveData<Boolean>(false)
    val isBtnSearchClicked: LiveData<Boolean> get() = _isBtnSearchClicked

    private val _back = MutableLiveData<Boolean>(false)
    val back: LiveData<Boolean> get() = _back

    fun setReplaceFragment(flag: Boolean) {
        _replaceFragment.value = flag
    }

    fun setAlbumTagName(position: Int, label: String?) {
        _albumPosition.value = position
        _albumTagName.value = label
    }

    fun setIsBtnSearchClicked(flag: Boolean) {
        _isBtnSearchClicked.value = flag
    }

    fun setSearchTagName(label: String) {
        _albumSearchTagName.value = label
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
