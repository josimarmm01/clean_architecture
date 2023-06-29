package com.example.josimar.framework

import androidx.paging.PagingSource
import com.example.core.data.repository.MovieRemoteDataSorce
import com.example.core.data.repository.MoviesRepository
import com.example.core.domain.model.Movie
import com.example.core.domain.model.MovieDetail
import com.example.josimar.framework.page.MoviePageSorce
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSorce: MovieRemoteDataSorce): MoviesRepository {
    override fun getMovies(page: Int): PagingSource<Int, Movie> {
        return MoviePageSorce(remoteDataSorce, page)
    }
    override suspend fun getDetailMovie(idMovie: Int): MovieDetail {
        return remoteDataSorce.fetMovieDetail(idMovie = idMovie)
    }
}