package com.example.pexelsapp.domain.usecase

import com.example.pexelsapp.domain.PhotoRepository
import com.example.pexelsapp.domain.model.PhotoModel
import com.example.pexelsapp.domain.usecase.interfaces.GetPhotoByIdApiUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPhotoByIdApiUseCaseImpl @Inject constructor(
    private val photoRepository: PhotoRepository
): GetPhotoByIdApiUseCase {
    override suspend fun execute(id: Int): Flow<PhotoModel> {
        return photoRepository.getPhotoByIdApi(id)
    }

}