package com.example.josimar.framework.di

import com.example.core.usecase.AddFavoritesUserCase
import com.example.core.usecase.AddFavoritesUserCaseImpl
import com.example.core.usecase.GetMovieDetailUseCase
import com.example.core.usecase.GetMovieDetailUseCaseImpl
import com.example.core.usecase.GetMoviesUseCase
import com.example.core.usecase.GetMoviesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindMoviesUseCase(useCase: GetMoviesUseCaseImpl): GetMoviesUseCase

    @Binds
    fun bindMovieDetailUserCase(useCase: GetMovieDetailUseCaseImpl): GetMovieDetailUseCase

    @Binds
    fun bindAddFavoritesUseCase(useCase: AddFavoritesUserCaseImpl): AddFavoritesUserCase

}
