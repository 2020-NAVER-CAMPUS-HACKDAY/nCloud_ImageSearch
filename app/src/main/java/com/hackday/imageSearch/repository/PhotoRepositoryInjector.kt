package com.hackday.imageSearch.repository

object PhotoRepositoryInjector {
    fun getPhotoRepositoryImpl(): PhotoRepositoryImpl {
        return PhotoRepositoryImpl.getInstance()
    }
}