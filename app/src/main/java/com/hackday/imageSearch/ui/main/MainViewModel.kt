package com.hackday.imageSearch.ui.main

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _replaceFragment = MutableLiveData<Boolean>(false)
    val replaceFragment: LiveData<Boolean> get() = _replaceFragment

    private val _albumTagTitle = MutableLiveData<String>("")
    val albumTagTitle: LiveData<String> get() = _albumTagTitle

    private val _searchTagName = MutableLiveData<String>("")
    val searchTagName: LiveData<String> get() = _searchTagName

    private val _isBtnSearchClicked = MutableLiveData<Boolean>(false)
    val isBtnSearchClicked: LiveData<Boolean> get() = _isBtnSearchClicked

    private val _back = MutableLiveData<Boolean>(false)
    val back: LiveData<Boolean> get() = _back

    private val _editSearch = ObservableField<String>("")
    val editSearch: ObservableField<String> get() = _editSearch

    fun setReplaceFragment(flag: Boolean) {
        _replaceFragment.value = flag
    }

    fun setAlbumTagTitle(label: String?) {
        _albumTagTitle.value = label
    }

    fun setIsBtnSearchClicked(flag: Boolean) {
        _isBtnSearchClicked.value = flag
    }

    fun setSearchTagName(tag: String) {
        _searchTagName.value = tag
    }

    fun setBack() {
        _back.value = true
    }

    fun setEditSearchEmpty(){
        _editSearch.set("")
    }
}
