package com.hackday.imageSearch.model

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photoinfo")
data class PhotoInfo(
    @ColumnInfo(name = "date")
    var date : String, // yy-mm-dd? hh-mm-ss
    @PrimaryKey
    @ColumnInfo(name = "uri")
    var uri: String, //Uri 가 room DB에 저장이 안되서 String을 사용, Uri.parse(String)으로 변환
    @ColumnInfo(name = "tag1") //ML kit 실행 후 정확도가 높은 태그 순위 3개까지 사용
    var tag1 : String?,
    @ColumnInfo(name = "tag2")
    var tag2 : String?,
    @ColumnInfo(name = "tag3")
    var tag3 : String?
)