package com.hackday.imageSearch.ui.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hackday.imageSearch.database.model.PhotoTag
import com.hackday.imageSearch.repository.PhotoInfoRepositoryInjector
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver

class AlbumViewModel : ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()
    private val repository = PhotoInfoRepositoryInjector.getPhotoRepositoryImpl()

    private val _itemList = MutableLiveData<List<PhotoTag>>(arrayListOf())
    val itemList: LiveData<List<PhotoTag>> get() = _itemList

    fun getTagName() {
        disposable.add(
            repository.getAllTag()
                .subscribeWith(object : DisposableSingleObserver<List<PhotoTag>>() {
                    override fun onSuccess(t: List<PhotoTag>) {
                        _itemList.value = t
                        //Log.d("MMMMM", t.toString())
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
