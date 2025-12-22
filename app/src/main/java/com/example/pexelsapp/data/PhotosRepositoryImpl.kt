package com.example.pexelsapp.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.pexelsapp.data.remote.PexelsApi
import com.example.pexelsapp.data.remote.PhotosRemoteMediator
import com.example.pexelsapp.domain.PhotoRepository
import com.example.pexelsapp.domain.model.PhotoModel
import com.example.pexelsapp.data.database.PexelsDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

@OptIn(ExperimentalPagingApi::class)
class PhotosRepositoryImpl(
    private val api: PexelsApi,
    private val database: PexelsDatabase,
) : PhotoRepository {

    override suspend fun getPhotos(query: String): Flow<PagingData<PhotoModel>> {
        return withContext(Dispatchers.IO) {
            val pager = Pager(
                config = PagingConfig(pageSize = 20),
                remoteMediator = PhotosRemoteMediator(
                    query = query,
                    database = database,
                    api = api,
                ),
                pagingSourceFactory = { database.pexelsDao.pagingSource() }
            )

            pager.flow.map { pagingData ->
                pagingData.map { it.toModel() }
            }
        }
    }

    override suspend fun getFeatureCollections(): List<String> {
        TODO()
    }
}