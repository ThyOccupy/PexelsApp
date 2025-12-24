package com.example.pexelsapp.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.pexelsapp.data.source.PexelsApi
import com.example.pexelsapp.data.source.PhotosRemoteMediator
import com.example.pexelsapp.domain.PhotoRepository
import com.example.pexelsapp.domain.model.PhotoModel
import com.example.pexelsapp.data.database.PexelsDatabase
import com.example.pexelsapp.utils.Const
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PhotosRepositoryImpl @Inject constructor(
    private val api: PexelsApi,
    private val database: PexelsDatabase,
) : PhotoRepository {

    override suspend fun getPhotos(query: String): Flow<PagingData<PhotoModel>> {
        return withContext(Dispatchers.IO) {
            val pager = Pager(
                config = PagingConfig(Const.DEFAULT_CURATED_LIMIT),
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
        return withContext(Dispatchers.IO) {
            return@withContext api.getFeaturedCollections().collection
                .map { collection ->
                    collection.title
                }
        }
    }

    override suspend fun getPhotoByIdApi(id: Int): Flow<PhotoModel> {
        return flow {
            val photoModel = api.getPhotoById(id = id).toEntity().toModel()
            emit(photoModel)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getPhotoByIdDb(id: Int): Flow<PhotoModel> {
        return withContext(Dispatchers.IO) {
            return@withContext database.pexelsDao.getFromDbById(id).map {
                it.toModel()
            }
        }
    }

    override suspend fun switchBookmarkStatus(id: Int, isBookmarked: Boolean) {
        database.pexelsDao.switchBookmarkStatus(id, isBookmarked)
    }

    override suspend fun getBookmarks(): Flow<PagingData<PhotoModel>> {
        return withContext(Dispatchers.IO) {
            val pager = Pager (
                config = PagingConfig(Const.DEFAULT_CURATED_LIMIT),
                pagingSourceFactory = {
                    database.pexelsDao.getBookmarked()
                }
            )

            pager.flow.map { pagingData ->
                pagingData.map { it.toModel() }
            }
        }
    }
}