package com.hackday.imageSearch.ml

import android.app.Application
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.hackday.imageSearch.model.PhotoInfo

class MLViewModel:ViewModel() {

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
}