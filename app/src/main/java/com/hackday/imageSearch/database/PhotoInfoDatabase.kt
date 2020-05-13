package com.hackday.imageSearch.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hackday.imageSearch.MyApplication
import com.hackday.imageSearch.model.PhotoInfo

@Database(entities = [PhotoInfo::class], version = 1, exportSchema = false)
abstract class PhotoInfoDatabase : RoomDatabase() {
    abstract fun photoInfoDao(): PhotoInfoDao

    companion object{
        private const val DB_NAME = "photoinfo.db"

        @JvmStatic
        fun getDatabase(): PhotoInfoDatabase{
            return Room.databaseBuilder(MyApplication.applicationContext(), PhotoInfoDatabase::class.java, DB_NAME)
                .build()
        }
    }
}