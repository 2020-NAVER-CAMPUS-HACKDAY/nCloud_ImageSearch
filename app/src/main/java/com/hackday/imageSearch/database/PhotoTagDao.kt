package com.hackday.imageSearch.database

import androidx.room.*
import com.hackday.imageSearch.database.model.PhotoTag
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PhotoTagDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTag(photoTag: PhotoTag?): Completable

    @Query("SELECT * FROM phototag")
    fun getAllTag(): Single<List<PhotoTag>>

    @Query("DELETE FROM phototag")
    fun deleteAllTag(): Completable

    @Delete
    fun deleteTag(photoTag: PhotoTag?): Completable
}