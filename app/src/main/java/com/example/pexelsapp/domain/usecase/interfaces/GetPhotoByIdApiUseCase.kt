package com.example.pexelsapp.domain.usecase.interfaces

import com.example.pexelsapp.domain.model.PhotoModel
import kotlinx.coroutines.flow.Flow

interface GetPhotoByIdApiUseCase {
    suspend fun execute(id: Int): Flow<PhotoModel>
}