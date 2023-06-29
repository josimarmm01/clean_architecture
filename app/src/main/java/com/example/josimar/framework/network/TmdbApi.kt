package com.example.josimar.framework.network

import com.example.josimar.framework.network.response.DataWrapperResponse
import com.example.josimar.framework.network.response.MovieDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("/3/movie/popular")
    suspend fun getMovies(@Query("page") page: Int) : DataWrapperResponse

    @GET("/3/movie/now_playing")
    suspend fun getMoviesNowPlaying(@Query("page") page: Int) : DataWrapperResponse

    @GET("/3/movie/{idMovie}")
    suspend fun getDetailMovie(@Path("idMovie") idMovie: Int) : MovieDetailResponse
}