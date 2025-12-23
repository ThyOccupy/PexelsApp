package com.example.pexelsapp.domain.usecase

import androidx.paging.PagingData
import com.example.pexelsapp.domain.PhotoRepository
import com.example.pexelsapp.domain.model.PhotoModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPhotosUseCaseImpl @Inject constructor(
    private val photoRepository: PhotoRepository
): GetPhotosUseCase {
    override suspend fun execute(query: String): Flow<PagingData<PhotoModel>> {
        return photoRepository.getPhotos(query)
    }
}