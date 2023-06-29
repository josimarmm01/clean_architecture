package com.example.josimar.framework.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.data.DbConstatns
import com.example.core.domain.model.Movie

@Entity(tableName = DbConstatns.FAVORITES_TABLE_NAME)
data class MovieFavoriteEntity(
    @PrimaryKey
    @ColumnInfo(name = DbConstatns.FAVORITES_COLUMN_ID)
    val id: Int,
    @ColumnInfo(name = DbConstatns.FAVORITES_COLUMN_NAME)
    val name: String,
    @ColumnInfo(name = DbConstatns.FAVORITES_COLUMN_IMG_URL)
    val imgUrl: String
)

fun List<MovieFavoriteEntity>.toMoviesModel() = map {
    Movie(it.id, it.name, it.imgUrl)
}