package com.hackday.imageSearch.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.hackday.imageSearch.database.PhotoInfoDatabase
import com.hackday.imageSearch.database.model.PhotoTag
import com.hackday.imageSearch.model.PhotoInfo
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

    override fun getAll(): DataSource.Factory<Int, PhotoInfo> {
        return photoInfoDao.getAll()
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

    override fun getPhotoByUri(uri: String): LiveData<PhotoInfo> {
        return photoInfoDao
            .getPhotoByUri(uri)
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

    override fun getPhotoByTag(tag: String): DataSource.Factory<Int, PhotoInfo> {
        return photoInfoDao.getPhotoByTag(tag)
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

    override fun getUriCountbyUri(uri: String): Boolean {
        return photoInfoDao.getUriCountbyUri(uri)
    }

    override fun insertPhotoNonObserve(photoInfo: PhotoInfo?) {
        return photoInfoDao.insertPhotoNonObserve(photoInfo)
    }

    override fun insertTagNonObserve(photoTag: PhotoTag?) {
        return photoTagDao.insertTagNonObserve(photoTag)
    }

    override fun insertTag(photoTag: PhotoTag?): Completable {
        return photoTagDao
            .insertTag(photoTag)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun insertTagList(photoTagList: ArrayList<PhotoTag>): Completable {
        return photoTagDao
            .insertTagList(photoTagList)
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