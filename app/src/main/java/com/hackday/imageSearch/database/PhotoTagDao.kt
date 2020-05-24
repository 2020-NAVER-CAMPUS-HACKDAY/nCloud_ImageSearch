package com.hackday.imageSearch.database

import androidx.paging.DataSource
import androidx.room.*
import com.hackday.imageSearch.database.model.PhotoTag
import io.reactivex.Completable

@Dao
interface PhotoTagDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTag(photoTag: PhotoTag?): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTagNonObserve(photoTag: PhotoTag?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTagList(photoTagList: ArrayList<PhotoTag>?): Completable

    @Query("SELECT * FROM phototag")
    fun getAllTag(): DataSource.Factory<Int, PhotoTag>


    @Query("DELETE FROM phototag")
    fun deleteAllTag(): Completable

    @Delete
    fun deleteTag(photoTag: PhotoTag?): Completable
}