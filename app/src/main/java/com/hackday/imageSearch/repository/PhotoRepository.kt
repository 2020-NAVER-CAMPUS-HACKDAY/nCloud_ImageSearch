package com.hackday.imageSearch.repository

import androidx.lifecycle.LiveData
import com.hackday.imageSearch.model.Photo
import io.reactivex.Completable
import io.reactivex.Single

interface PhotoRepository{
    fun getAll(): Single<List<Photo>>
    fun deleteAll(): Completable
    fun delete(photo: Photo): Completable
    fun getPhotoById(photoId: String): Single<Photo>
    fun getPhotoByTag1(tag1: String): Single<Photo>
    fun getPhotoByTag2(tag2: String): Single<Photo>
    fun getPhotoByTag3(tag3: String): Single<Photo>
    fun insertPhoto(photo: Photo): Completable
    fun insertPhotoList(photoList: ArrayList<Photo>): Completable
}