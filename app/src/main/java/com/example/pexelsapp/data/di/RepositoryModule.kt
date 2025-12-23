package com.example.pexelsapp.data.di

import com.example.pexelsapp.data.PhotosRepositoryImpl
import com.example.pexelsapp.domain.PhotoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindPhotosRepository(
        photosRepositoryImpl: PhotosRepositoryImpl
    ): PhotoRepository

}