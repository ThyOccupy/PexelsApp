package com.example.pexelsapp.domain

import androidx.paging.PagingData
import com.example.pexelsapp.domain.model.PhotoModel
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    suspend fun getPhotos(query: String): Flow<PagingData<PhotoModel>>
    suspend fun getFeatureCollections(): List<String>
    suspend fun getPhotoByIdApi(id: Int): Flow<PhotoModel>
    suspend fun getPhotoByIdDb(id: Int): Flow<PhotoModel>
}