package com.example.josimar.framework.di

import com.example.core.data.repository.MovieRemoteDataSorce
import com.example.core.data.repository.MoviesRepository
import com.example.josimar.framework.MovieRepositoryImpl
import com.example.josimar.framework.remote.RetrofitMoviesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MoviesRepositoryModule {

    @Binds
    fun bindMovieRepository(repository: MovieRepositoryImpl): MoviesRepository

    @Binds
    fun bindRemoteDataSorce(dataSorce: RetrofitMoviesDataSource): MovieRemoteDataSorce

}