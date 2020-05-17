package com.hackday.imageSearch.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hackday.imageSearch.MyApplication
import com.hackday.imageSearch.database.model.PhotoTag
import com.hackday.imageSearch.model.PhotoInfo

@Database(entities = [PhotoInfo::class, PhotoTag::class], version = 1, exportSchema = false)
abstract class PhotoInfoDatabase : RoomDatabase() {
    abstract fun photoInfoDao(): PhotoInfoDao
    abstract fun photoTagDao(): PhotoTagDao

    companion object{
        private const val DB_NAME = "photoinfo.db"

        private var INSTANCE: PhotoInfoDatabase? = null

        fun getInstance(): PhotoInfoDatabase{
            if(INSTANCE == null){
                synchronized(PhotoInfoDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        MyApplication.applicationContext(),
                        PhotoInfoDatabase::class.java,
                        DB_NAME
                    )
                        .build()
                }
            }
            return INSTANCE as PhotoInfoDatabase
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}