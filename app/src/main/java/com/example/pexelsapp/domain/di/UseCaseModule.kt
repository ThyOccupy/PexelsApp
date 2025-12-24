package com.example.pexelsapp.domain.di

import com.example.pexelsapp.domain.usecase.DownloadImageUseCaseImpl
import com.example.pexelsapp.domain.usecase.interfaces.GetHeaderUseCase
import com.example.pexelsapp.domain.usecase.GetHeaderUseCaseImpl
import com.example.pexelsapp.domain.usecase.GetPhotoByIdApiUseCaseImpl
import com.example.pexelsapp.domain.usecase.GetPhotoByIdDbUseCaseImpl
import com.example.pexelsapp.domain.usecase.interfaces.GetPhotosUseCase
import com.example.pexelsapp.domain.usecase.GetPhotosUseCaseImpl
import com.example.pexelsapp.domain.usecase.interfaces.DownloadImageUseCase
import com.example.pexelsapp.domain.usecase.interfaces.GetPhotoByIdApiUseCase
import com.example.pexelsapp.domain.usecase.interfaces.GetPhotoByIdDbUseCase
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

    @Binds
    abstract fun bindGetHeadersUseCase(
        getHeaderUseCaseImpl: GetHeaderUseCaseImpl
    ): GetHeaderUseCase

    @Binds
    abstract fun bindGetPhotoByIdDbUseCase(
        getPhotoByIdUseCaseImpl: GetPhotoByIdDbUseCaseImpl
    ): GetPhotoByIdDbUseCase

    @Binds
    abstract fun bindGetPhotoByIdApiUseCase(
        getPhotoByIdUseCaseImpl: GetPhotoByIdApiUseCaseImpl
    ): GetPhotoByIdApiUseCase

    @Binds
    abstract fun bindDownloadImageUseCase(
        downloadImageUseCaseImpl: DownloadImageUseCaseImpl
    ): DownloadImageUseCase



}