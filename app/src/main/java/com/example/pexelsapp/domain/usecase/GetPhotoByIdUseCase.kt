package com.example.pexelsapp.domain.usecase

import com.example.pexelsapp.domain.model.PhotoModel
import kotlinx.coroutines.flow.Flow

interface GetPhotoByIdUseCase {
    suspend fun execute(id: Int): Flow<PhotoModel>

}