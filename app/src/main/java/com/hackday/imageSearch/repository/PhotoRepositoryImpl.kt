package com.hackday.imageSearch.repository

import androidx.lifecycle.LiveData
import com.hackday.imageSearch.model.Photo
import com.hackday.imageSearch.database.PhotoDatabase
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PhotoRepositoryImpl : PhotoRepository {
    private val photoDao = PhotoDatabase.getDatabase()
        .photoInfoDao()

    companion object{
        @Volatile
        private var instance: PhotoRepositoryImpl? = null

        fun getInstance() =
            instance
                ?: synchronized(this){
                instance
                    ?: PhotoRepositoryImpl()
                        .also { instance = it }
            }
    }

    override fun getAll(): Single<List<Photo>> {
        return photoDao.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun deleteAll(): Completable {
        return photoDao.deleteAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun delete(photo: Photo): Completable {
        return photoDao.delete(photo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getPhotoById(photoId: String): Single<Photo> {
        return photoDao
            .getPhotoById(photoId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getPhotoByTag1(tag1: String): Single<Photo> {
        return photoDao
            .getPhotoByTag1(tag1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getPhotoByTag2(tag2: String): Single<Photo> {
        return photoDao
            .getPhotoByTag2(tag2)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getPhotoByTag3(tag3: String): Single<Photo> {
        return photoDao
            .getPhotoByTag3(tag3)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun insertPhoto(photo: Photo): Completable {
        return photoDao
            .insertPhoto(photo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun insertPhotoList(photoList: ArrayList<Photo>): Completable {
        return photoDao
            .insertPhotoList(photoList)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}