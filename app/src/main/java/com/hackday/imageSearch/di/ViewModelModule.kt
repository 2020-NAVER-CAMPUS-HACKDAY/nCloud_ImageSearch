package com.hackday.imageSearch.di

import com.hackday.imageSearch.repository.PhotoInfoRepositoryInjector
import com.hackday.imageSearch.ui.album.AlbumViewModel
import com.hackday.imageSearch.ui.main.MainViewModel
import com.hackday.imageSearch.ui.photo.PhotoViewModel
import com.hackday.imageSearch.ui.photoinfo.PhotoInfoViewModel
import com.hackday.imageSearch.ui.viewer.ViewerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { PhotoViewModel(get()) }
    viewModel { AlbumViewModel(get()) }
    viewModel { ViewerViewModel() }
    viewModel { PhotoInfoViewModel(PhotoInfoRepositoryInjector.getPhotoRepositoryImpl()) }
}