package com.hackday.imageSearch.repository

import com.hackday.imageSearch.database.model.PhotoTag
import io.reactivex.Completable
import io.reactivex.Single

interface PhotoTagRepository {
    fun insertTag(photoTag: PhotoTag?): Completable
    fun insertTagList(photoTagList: ArrayList<PhotoTag>): Completable
    fun getAllTag(): Single<List<PhotoTag>>
    fun deleteAllTag(): Completable
    fun deleteTag(photoTag: PhotoTag?): Completable
}