package com.example.pexelsapp.domain.usecase

import com.example.pexelsapp.domain.PhotoRepository
import com.example.pexelsapp.domain.usecase.interfaces.GetHeaderUseCase
import javax.inject.Inject

class GetHeaderUseCaseImpl @Inject constructor(
    private val photoRepository: PhotoRepository
) : GetHeaderUseCase {
    override suspend fun execute(): List<String> {
        return photoRepository.getFeatureCollections()
    }
}