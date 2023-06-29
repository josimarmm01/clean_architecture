package com.example.josimar.framework.di

import com.example.core.data.repository.FavoritesLocalDataSorce
import com.example.core.data.repository.FavoritesRepository
import com.example.josimar.framework.FavoritesRepositoryImpl
import com.example.josimar.framework.local.RoomFavoritesDataSorce
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FavoritesRepositoryModule {

    @Binds
    fun bindFavoritesRepository(repository: FavoritesRepositoryImpl): FavoritesRepository

    @Binds
    fun bindLocalDataSorce(dataSorce: RoomFavoritesDataSorce): FavoritesLocalDataSorce

}