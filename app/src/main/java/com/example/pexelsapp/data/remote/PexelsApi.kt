package com.example.pexelsapp.data.remote

import com.example.pexelsapp.Const.FEATURED_NUMBER
import com.example.pexelsapp.data.remote.dto.GetQueryPhotosResponseBody
import com.example.pexelsapp.data.remote.dto.QueryCollectionsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PexelsApi {

    @GET("search")
    suspend fun getPhotosByQuery(
        @Query(QUERY_PARAM_QUERY) query: String,
        @Query(QUERY_PARAM_PAGE) page: Int,
        @Query(QUERY_PARAM_PER_PAGE) pageCount: Int
    ):  GetQueryPhotosResponseBody

    @GET("curated")
    suspend fun getPopularPhotos(
        @Query(QUERY_PARAM_PAGE) page: Int,
        @Query(QUERY_PARAM_PER_PAGE) pageCount: Int
    ): GetQueryPhotosResponseBody

    @GET("collections/featured")
    suspend fun getFeaturedCollections(
        @Query("per_page") pageSize: Int = FEATURED_NUMBER
    ): QueryCollectionsDto

    companion object {
        private const val QUERY_PARAM_QUERY = "query"
        private const val QUERY_PARAM_PAGE = "page"
        private const val QUERY_PARAM_PER_PAGE = "per_page"
    }
}