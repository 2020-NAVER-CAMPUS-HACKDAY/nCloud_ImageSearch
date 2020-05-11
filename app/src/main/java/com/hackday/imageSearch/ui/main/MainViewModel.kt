package com.hackday.imageSearch.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _sample = MutableLiveData<Int>()
    val sample : LiveData<Int> get() = _sample
}
