package com.example.pexelsapp.domain.di

import com.example.pexelsapp.domain.usecase.GetPhotosUseCase
import com.example.pexelsapp.domain.usecase.GetPhotosUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindGetPhotosUseCase(
        getPhotosUseCaeImpl: GetPhotosUseCaseImpl
    ): GetPhotosUseCase
}