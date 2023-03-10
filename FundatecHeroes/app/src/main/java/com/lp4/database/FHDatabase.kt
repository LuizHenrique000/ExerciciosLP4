package com.lp4.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lp4.App
import com.lp4.profile.data.local.UserDao
import com.lp4.profile.data.local.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class FHDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        fun getInstance(): FHDatabase {
            return Room.databaseBuilder(
                App.context,
                FHDatabase::class.java,
                "fh_database"
            )
                .build()
        }
    }
}