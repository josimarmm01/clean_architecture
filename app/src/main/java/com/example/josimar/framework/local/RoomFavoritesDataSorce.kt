package com.example.josimar.framework.local

import com.example.core.data.repository.FavoritesLocalDataSorce
import com.example.core.domain.model.Movie
import com.example.josimar.framework.db.dao.FavoriteDao
import com.example.josimar.framework.db.entity.MovieFavoriteEntity
import com.example.josimar.framework.db.entity.toMoviesModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomFavoritesDataSorce @Inject constructor(
    private val favoriteDao: FavoriteDao
): FavoritesLocalDataSorce {
    override fun getAll(): Flow<List<Movie>> {
        return favoriteDao.loadFavorites().map {
            it.toMoviesModel()
        }
    }

    override suspend fun save(movie: Movie) {
        favoriteDao.insertFavorites(movie.toFavoriteEntity())
    }

    override suspend fun delete(movie: Movie) {
        favoriteDao.deleteFavorite(movie.toFavoriteEntity())
    }

    private fun Movie.toFavoriteEntity()
    = MovieFavoriteEntity(id = idMovie, name = name, imgUrl = igmUrl)
}
