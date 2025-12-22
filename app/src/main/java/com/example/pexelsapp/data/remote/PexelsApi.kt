package com.example.pexelsapp.data.remote

import com.example.pexelsapp.data.remote.dto.GetCollectionResponseBody
import com.example.pexelsapp.data.remote.dto.GetQueryPhotosResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface PexelsApi {

    @GET("search")
    suspend fun getPhotosByQuery(
        @Query(QUERY_PARAM_QUERY) query: String
    ): GetQueryPhotosResponseBody

    @GET("collections/featured")
    suspend fun getFeaturedCollections(): GetCollectionResponseBody

    companion object {
        private const val QUERY_PARAM_QUERY = "query"
    }
}