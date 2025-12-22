package com.example.pexelsapp.data.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.pexelsapp.data.database.PexelsDatabase
import com.example.pexelsapp.data.database.entities.PexelsEntity
import com.example.pexelsapp.data.toEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PhotosRemoteMediator(
    private val database: PexelsDatabase,
    private val api: PexelsApi,
    private val query: String
): RemoteMediator<Int, PexelsEntity>() {

    private var page = 1
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PexelsEntity>,
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> {
                    page = 1
                    page
                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        page = 1
                    } else {
                        page += 1
                    }
                    page
                }
            }

            Log.d("RemoteMediator", "Requesting page $loadKey")


            val photos = if (query.isEmpty()) {
                api.getPopularPhotos(page = loadKey, pageCount = state.config.pageSize)
            } else {
                api.getPhotosByQuery(query = query, page = loadKey, pageCount = state.config.pageSize)
            }

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.pexelsDao.clearAll()
                }

                val photosEntities = photos.photos.map { it.toEntity() }
                database.pexelsDao.upsertAll(photosEntities)
            }

            Log.d("RemoteMediator", "Finished transaction: ${photos.photos.isEmpty()}")


            MediatorResult.Success(
                endOfPaginationReached = photos.photos.isEmpty()
            )

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}