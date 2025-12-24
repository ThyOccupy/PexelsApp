package com.example.pexelsapp.domain.usecase.interfaces

import com.example.pexelsapp.domain.model.PhotoModel
import kotlinx.coroutines.flow.Flow

interface GetPhotoByIdDbUseCase {
    suspend fun execute(id: Int): Flow<PhotoModel>

}