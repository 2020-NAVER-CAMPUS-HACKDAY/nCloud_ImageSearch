package com.hackday.imageSearch.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hackday.imageSearch.MyApplication
import com.hackday.imageSearch.model.Photo

@Database(entities = [Photo::class], version = 1, exportSchema = false)
abstract class PhotoDatabase : RoomDatabase() {
    abstract fun photoInfoDao(): PhotoDao

    companion object{
        private const val DB_NAME = "photo.db"

        @JvmStatic
        fun getDatabase(): PhotoDatabase{
            return Room.databaseBuilder(MyApplication.applicationContext(), PhotoDatabase::class.java, DB_NAME)
                .build()
        }
    }
}