package com.hackday.imageSearch.database

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.room.*
import com.hackday.imageSearch.model.PhotoInfo
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PhotoInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhoto(photoInfo: PhotoInfo?): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhotoList(photoInfoList: ArrayList<PhotoInfo>?): Completable

    @Query("SELECT * FROM photoinfo")
    fun getAll(): Single<List<PhotoInfo>>

    @Query("DELETE FROM photoinfo")
    fun deleteAll(): Completable

    @Delete
    fun delete(photoInfo: PhotoInfo?): Completable

    @Query("SELECT * FROM photoinfo WHERE id= :id")
    fun getPhotoById(id: String): Single<PhotoInfo>

    @Query("SELECT * FROM photoinfo WHERE uri= :uri")
    fun getPhotoByUri(uri: String): Single<PhotoInfo>

    @Query("SELECT * FROM photoinfo WHERE tag1= :tag1")
    fun getPhotoByTag1(tag1: String): Single<List<PhotoInfo>>

    @Query("SELECT * FROM photoinfo WHERE tag2= :tag2")
    fun getPhotoByTag2(tag2: String): Single<List<PhotoInfo>>

    @Query("SELECT * FROM photoinfo WHERE tag3= :tag3")
    fun getPhotoByTag3(tag3: String): Single<List<PhotoInfo>>
}