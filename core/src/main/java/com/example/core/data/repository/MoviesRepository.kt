package com.example.core.data.repository

import androidx.paging.PagingSource
import com.example.core.domain.model.Movie
import com.example.core.domain.model.MovieDetail

interface MoviesRepository {

    fun getMovies(page: Int) : PagingSource<Int, Movie>

    suspend fun getDetailMovie(idMovie: Int): MovieDetail
}
