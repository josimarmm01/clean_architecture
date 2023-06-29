package com.example.core.data.repository

import com.example.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface FavoritesLocalDataSorce {

    fun getAll(): Flow<List<Movie>>

    suspend fun save(movie: Movie)

    suspend fun delete(movie: Movie)

}