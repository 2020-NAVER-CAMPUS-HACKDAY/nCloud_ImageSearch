package com.hackday.imageSearch.di

import com.hackday.imageSearch.ui.main.AlbumViewModel
import com.hackday.imageSearch.ui.main.MainViewModel
import com.hackday.imageSearch.ui.main.PhotoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { PhotoViewModel() }
    viewModel { AlbumViewModel() }
}