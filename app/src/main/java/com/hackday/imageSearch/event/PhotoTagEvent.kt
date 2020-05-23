package com.hackday.imageSearch.event

import com.hackday.imageSearch.database.model.PhotoTag
import io.reactivex.subjects.PublishSubject

object PhotoTagEvent {
    val addPhotoTagListSubject = PublishSubject.create<PhotoTag>()
    fun addPhotoTagList(photoTag: PhotoTag){
        addPhotoTagListSubject.onNext(photoTag)
    }
}