package com.example.josimar.framework

import com.example.core.data.repository.FavoritesLocalDataSorce
import com.example.core.data.repository.FavoritesRepository
import com.example.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val favoritesDataSorce: FavoritesLocalDataSorce
): FavoritesRepository {
    override fun getAll(): Flow<List<Movie>> {
        return favoritesDataSorce.getAll()
    }

    override suspend fun saveFavorite(movie: Movie) {
        favoritesDataSorce.save(movie)
    }

    override suspend fun deleteFavorite(movie: Movie) {
        favoritesDataSorce.delete(movie)
    }
}