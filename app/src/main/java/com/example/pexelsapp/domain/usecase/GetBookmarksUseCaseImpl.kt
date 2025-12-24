package com.example.pexelsapp.domain.usecase

import androidx.paging.PagingData
import com.example.pexelsapp.domain.PhotoRepository
import com.example.pexelsapp.domain.model.PhotoModel
import com.example.pexelsapp.domain.usecase.interfaces.GetBookmarksUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookmarksUseCaseImpl @Inject constructor(
    private val repository: PhotoRepository
): GetBookmarksUseCase {
    override suspend fun execute(): Flow<PagingData<PhotoModel>> {
        return repository.getBookmarks()
    }
}