package com.example.pexelsapp.domain.usecase.interfaces

import androidx.paging.PagingData
import com.example.pexelsapp.domain.model.PhotoModel
import kotlinx.coroutines.flow.Flow

interface GetPhotosUseCase {
    suspend fun execute(query:String) : Flow<PagingData<PhotoModel>>

}