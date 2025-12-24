package com.example.pexelsapp.domain.usecase

import com.example.pexelsapp.domain.PhotoRepository
import com.example.pexelsapp.domain.model.PhotoModel
import com.example.pexelsapp.domain.usecase.interfaces.GetPhotoByIdDbUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPhotoByIdDbUseCaseImpl @Inject constructor(
    private val photoRepository: PhotoRepository
): GetPhotoByIdDbUseCase {
    override suspend fun execute(id: Int): Flow<PhotoModel> {
        return photoRepository.getPhotoByIdDb(id)
    }

}