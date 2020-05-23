package com.hackday.imageSearch.event

import com.hackday.imageSearch.model.PhotoInfo
import io.reactivex.subjects.PublishSubject

object PhotoInfoEvent {
    val addPhotoInfoListSubject = PublishSubject.create<PhotoInfo>()
    fun addPhotoInfoList(photoInfo: PhotoInfo){
        addPhotoInfoListSubject.onNext(photoInfo)
    }
}