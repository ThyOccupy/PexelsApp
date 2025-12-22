package com.example.pexelsapp.data.remote

import com.example.pexelsapp.BuildConfig
import com.example.pexelsapp.data.remote.dto.GetCollectionResponseBody
import com.example.pexelsapp.data.remote.dto.GetQueryPhotosResponseBody
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PexelsApi {

    @GET("search")
    suspend fun getPhotosByQuery(
        @Header(HEADER_PARAM_AUTHORIZATION) apiKey: String = BuildConfig.API_KEY,
        @Query(QUERY_PARAM_QUERY) query: String,
        @Query(QUERY_PARAM_PAGE) page: Int,
        @Query(QUERY_PARAM_PER_PAGE) pageCount: Int
    ):  GetQueryPhotosResponseBody

    @GET("curated")
    suspend fun getPopularPhotos(
        @Header(HEADER_PARAM_AUTHORIZATION) apiKey: String = BuildConfig.API_KEY,
        @Query(QUERY_PARAM_PAGE) page: Int,
        @Query(QUERY_PARAM_PER_PAGE) pageCount: Int
    ): GetQueryPhotosResponseBody

    @GET("collections/featured")
    suspend fun getFeaturedCollections(): GetCollectionResponseBody

    companion object {
        private const val QUERY_PARAM_QUERY = "query"
        private const val QUERY_PARAM_PAGE = "page"
        private const val QUERY_PARAM_PER_PAGE = "per_page"
        private const val HEADER_PARAM_AUTHORIZATION = "Authorization"
    }
}