package com.hackday.imageSearch.ml

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MLViewModel : ViewModel() {

    private val _current = MutableLiveData<Int>()
    val current: LiveData<Int> = _current

    private val _total = MutableLiveData<Int>()
    val total: LiveData<Int> = _total
    fun setCurrent(current: Int) {
        _current.postValue(current)
    }

    fun setTotal(total: Int) {
        _total.postValue(total)
    }
}