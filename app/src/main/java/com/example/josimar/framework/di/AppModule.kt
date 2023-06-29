package com.example.josimar.framework.di

import com.example.josimar.framework.imageloader.GlideImageLoader
import com.example.josimar.framework.imageloader.ImageLoad
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
interface AppModule {

    @Binds
    fun bindImageLoader(imageLoader: GlideImageLoader): ImageLoad

}