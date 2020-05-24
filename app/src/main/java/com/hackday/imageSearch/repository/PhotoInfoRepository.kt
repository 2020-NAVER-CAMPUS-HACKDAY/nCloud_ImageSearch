package com.hackday.imageSearch.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.hackday.imageSearch.model.PhotoInfo
import io.reactivex.Completable
import io.reactivex.Single

interface PhotoInfoRepository {
    fun getAll(): DataSource.Factory<Int, PhotoInfo>
    fun deleteAll(): Completable
    fun delete(photoInfo: PhotoInfo): Completable
    fun getPhotoByUri(uri: String): LiveData<PhotoInfo>
    fun getPhotoByTag1(tag1: String): Single<List<PhotoInfo>>
    fun getPhotoByTag2(tag2: String): Single<List<PhotoInfo>>
    fun getPhotoByTag3(tag3: String): Single<List<PhotoInfo>>
    fun insertPhoto(photoInfo: PhotoInfo): Completable
    fun insertPhotoList(photoInfoList: ArrayList<PhotoInfo>): Completable
    fun getUriCountbyUri(uri: String): Boolean
    fun insertPhotoNonObserve(photoInfo: PhotoInfo?)
}