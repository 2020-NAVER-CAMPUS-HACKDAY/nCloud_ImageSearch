package com.hackday.imageSearch.ml

import android.app.Application
import android.provider.ContactsContract
import android.view.View
import androidx.lifecycle.*
import com.hackday.imageSearch.model.PhotoInfo

class MLViewModel:ViewModel() {

    private val _progress = MutableLiveData<Int>()
    val progress:LiveData<Int> =_progress

    fun setProgress(progress:Int)
    {
        _progress.postValue(progress)
    }
}