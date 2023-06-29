package com.example.josimar.framework.remote

import com.example.core.data.repository.MovieRemoteDataSorce
import com.example.core.domain.model.MovieDetail
import com.example.core.domain.model.MoviePaging
import com.example.josimar.framework.network.TmdbApi
import com.example.josimar.framework.network.response.toMovieDetailModel
import com.example.josimar.framework.network.response.toMoviesModel
import javax.inject.Inject

class RetrofitMoviesDataSource @Inject constructor(

    private val tmdbApi: TmdbApi) : MovieRemoteDataSorce {

    override suspend fun fetchMovies(page: Int): MoviePaging {

        val data = tmdbApi.getMovies(page)
        val movies = data.results.map {
            it.toMoviesModel()
        }

        return MoviePaging(
            page = data.page,
            total = data.totalPages,
            movies = movies
        )
    }

    override suspend fun fetMovieDetail(idMovie: Int): MovieDetail {

        val data = tmdbApi.getDetailMovie(idMovie)
        return data.toMovieDetailModel()
    }
}
