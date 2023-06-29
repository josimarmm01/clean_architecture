package com.example.core.data.repository

import com.example.core.domain.model.MovieDetail
import com.example.core.domain.model.MoviePaging

interface MovieRemoteDataSorce {
    suspend fun fetchMovies(page: Int): MoviePaging

    suspend fun fetMovieDetail(idMovie: Int): MovieDetail
}