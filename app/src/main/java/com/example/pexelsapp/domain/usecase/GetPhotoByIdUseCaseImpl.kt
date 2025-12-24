package com.example.pexelsapp.domain.usecase

import com.example.pexelsapp.domain.PhotoRepository
import com.example.pexelsapp.domain.model.PhotoModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPhotoByIdUseCaseImpl @Inject constructor(
    private val photoRepository: PhotoRepository
): GetPhotoByIdUseCase {
    override suspend fun execute(id: Int): Flow<PhotoModel> {
        return photoRepository.getPhotoById(id)
    }

}