package com.danamon.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.danamon.data.entity.UserEntity
import com.danamon.local.dao.UserDao

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}