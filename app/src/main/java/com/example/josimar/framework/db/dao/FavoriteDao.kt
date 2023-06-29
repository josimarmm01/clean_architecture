package com.example.josimar.framework.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.data.DbConstatns
import com.example.josimar.framework.db.entity.MovieFavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM ${DbConstatns.FAVORITES_TABLE_NAME}")
    fun loadFavorites(): Flow<List<MovieFavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorites(favoriteEntity: MovieFavoriteEntity)

    @Delete
    suspend fun deleteFavorite(favoriteEntity: MovieFavoriteEntity)
}