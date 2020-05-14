package com.hackday.imageSearch.repository

import com.hackday.imageSearch.model.PhotoInfo
import io.reactivex.Completable
import io.reactivex.Single

interface PhotoInfoRepository{
    fun getAll(): Single<List<PhotoInfo>>
    fun deleteAll(): Completable
    fun delete(photoInfo: PhotoInfo): Completable
    fun getPhotoById(photoId: String): Single<PhotoInfo>
    fun getPhotoByUri(uri: String): Single<PhotoInfo>
    fun getPhotoByTag1(tag1: String): Single<PhotoInfo>
    fun getPhotoByTag2(tag2: String): Single<PhotoInfo>
    fun getPhotoByTag3(tag3: String): Single<PhotoInfo>
    fun insertPhoto(photoInfo: PhotoInfo): Completable
    fun insertPhotoList(photoInfoList: ArrayList<PhotoInfo>): Completable
}