package com.hackday.imageSearch.ml

import android.view.View
import androidx.lifecycle.*
import com.hackday.imageSearch.database.model.PhotoTag
import com.hackday.imageSearch.model.PhotoInfo
import com.hackday.imageSearch.repository.PhotoInfoRepositoryInjector
import io.reactivex.disposables.CompositeDisposable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MLViewModel : ViewModel() {
    private val _current = MutableLiveData<Int>()
    val current: LiveData<Int> = _current

    private val _total = MutableLiveData<Int>()
    val total:LiveData<Int> = _total

    fun setCurrent(current: Int) {
        _current.postValue(current)
    }

    fun setTotal(total: Int) {
        _total.postValue(total)
    }
}

