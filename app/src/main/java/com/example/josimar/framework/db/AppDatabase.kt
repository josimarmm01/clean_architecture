package com.example.josimar.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.josimar.framework.db.dao.FavoriteDao
import com.example.josimar.framework.db.entity.MovieFavoriteEntity

@Database(
    entities = [MovieFavoriteEntity::class],
    version = 1,
    exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

}