package com.hackday.imageSearch.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phototag")
data class PhotoTag(
    @PrimaryKey
    @ColumnInfo(name="tag")
    var tag: String,
    @ColumnInfo(name="uri")
    var uri: String
)