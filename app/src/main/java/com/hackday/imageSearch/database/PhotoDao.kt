package com.hackday.imageSearch.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hackday.imageSearch.model.Photo
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhoto(photo: Photo?): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhotoList(photoList: ArrayList<Photo>?): Completable

    @Query("SELECT * FROM photo")
    fun getAll(): Single<List<Photo>>

    @Query("DELETE FROM photo")
    fun deleteAll(): Completable

    @Delete
    fun delete(photo: Photo?): Completable

    @Query("SELECT * FROM photo WHERE id= :id")
    fun getPhotoById(id: String): Single<Photo>

    @Query("SELECT * FROM photo WHERE tag1= :tag1")
    fun getPhotoByTag1(tag1: String): Single<Photo>

    @Query("SELECT * FROM photo WHERE tag2= :tag2")
    fun getPhotoByTag2(tag2: String): Single<Photo>

    @Query("SELECT * FROM photo WHERE tag3= :tag3")
    fun getPhotoByTag3(tag3: String): Single<Photo>
}