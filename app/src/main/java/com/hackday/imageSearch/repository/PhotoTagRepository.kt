package com.hackday.imageSearch.repository

import androidx.paging.DataSource
import com.hackday.imageSearch.database.model.PhotoTag
import io.reactivex.Completable

interface PhotoTagRepository {
    fun insertTag(photoTag: PhotoTag?): Completable
    fun getAllTag(): DataSource.Factory<Int, PhotoTag>
    fun deleteAllTag(): Completable
    fun deleteTag(photoTag: PhotoTag?): Completable
}