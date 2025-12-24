package com.example.pexelsapp.domain.usecase.interfaces

import androidx.paging.PagingData
import com.example.pexelsapp.domain.model.PhotoModel
import kotlinx.coroutines.flow.Flow

interface GetBookmarksUseCase {
    suspend fun execute(): Flow<PagingData<PhotoModel>>
}