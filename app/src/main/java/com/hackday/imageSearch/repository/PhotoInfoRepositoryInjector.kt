package com.hackday.imageSearch.repository

object PhotoInfoRepositoryInjector {
    fun getPhotoRepositoryImpl(): PhotoInfoRepositoryImpl {
        return PhotoInfoRepositoryImpl.getInstance()
    }
}