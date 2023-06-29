package com.example.core.data.repository

import com.example.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    fun getAll(): Flow<List<Movie>>

    suspend fun saveFavorite(movie: Movie)

    suspend fun deleteFavorite(movie: Movie)

}