package com.hackday.imageSearch.model

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photoinfo")
data class PhotoInfo(
    @ColumnInfo(name = "id")
    var id : String?, //photo name?
    @ColumnInfo(name = "date")
    var date : String, // yy-mm-dd? hh-mm-ss
    @ColumnInfo(name = "gps") //장소를 위한 위치 저장 값
    var gps : String?, //Nullable로 변경, 필요시 위도, 경도
    @PrimaryKey
    @ColumnInfo(name = "uri")
    var uri: String, //Uri 가 room DB에 저장이 안되서 String을 사용, Uri.parse(String)으로 변환
    @ColumnInfo(name = "tag1") //ML kit 실행 후 정확도가 높은 태그 순위 3개까지 사용
    var tag1 : String,
    @ColumnInfo(name = "tag2")
    var tag2 : String,
    @ColumnInfo(name = "tag3")
    var tag3 : String
)