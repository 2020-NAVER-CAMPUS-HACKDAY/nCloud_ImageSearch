package com.hackday.imageSearch.repository

import com.hackday.imageSearch.model.PhotoInfo
import com.hackday.imageSearch.database.PhotoInfoDatabase
import com.hackday.imageSearch.database.model.PhotoTag
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PhotoInfoRepositoryImpl : PhotoInfoRepository, PhotoTagRepository {
    private val photoInfoDao = PhotoInfoDatabase.getInstance()
        .photoInfoDao()
    private val photoTagDao = PhotoInfoDatabase.getInstance()
        .photoTagDao()

    companion object{
        @Volatile
        private var INSTANCE: PhotoInfoRepositoryImpl? = null

        fun getInstance() =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: PhotoInfoRepositoryImpl().also { INSTANCE = it }
            }
    }

    override fun getAll(): Single<List<PhotoInfo>> {
        return photoInfoDao.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun deleteAll(): Completable {
        return photoInfoDao.deleteAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun delete(photoInfo: PhotoInfo): Completable {
        return photoInfoDao.delete(photoInfo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getPhotoById(photoId: String): Single<PhotoInfo> {
        return photoInfoDao
            .getPhotoById(photoId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getPhotoByUri(uri: String): Single<PhotoInfo> {
        return photoInfoDao
            .getPhotoByUri(uri)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getPhotoByTag1(tag1: String): Single<List<PhotoInfo>> {
        return photoInfoDao
            .getPhotoByTag1(tag1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getPhotoByTag2(tag2: String): Single<List<PhotoInfo>> {
        return photoInfoDao
            .getPhotoByTag2(tag2)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getPhotoByTag3(tag3: String): Single<List<PhotoInfo>> {
        return photoInfoDao
            .getPhotoByTag3(tag3)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun insertPhoto(photoInfo: PhotoInfo): Completable {
        return photoInfoDao
            .insertPhoto(photoInfo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun insertPhotoList(photoInfoList: ArrayList<PhotoInfo>): Completable {
        return photoInfoDao
            .insertPhotoList(photoInfoList)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun insertTag(photoTag: PhotoTag?): Completable {
        return photoTagDao
            .insertTag(photoTag)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getAllTag(): Single<List<PhotoTag>> {
        return photoTagDao
            .getAllTag()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun deleteAllTag(): Completable {
        return photoTagDao
            .deleteAllTag()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun deleteTag(photoTag: PhotoTag?): Completable {
        return photoTagDao
            .deleteTag(photoTag)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}