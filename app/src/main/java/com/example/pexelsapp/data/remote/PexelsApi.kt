package com.example.pexelsapp.data.remote

import com.example.pexelsapp.utils.Const.DEFAULT_CURATED_LIMIT
import com.example.pexelsapp.utils.Const.DEFAULT_FEATURED_NUMBER
import com.example.pexelsapp.data.remote.dto.GetQueryPhotosResponseBody
import com.example.pexelsapp.data.remote.dto.PhotoDto
import com.example.pexelsapp.data.remote.dto.QueryCollectionsDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
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
        @Query(QUERY_PARAM_PER_PAGE) pageCount: Int = DEFAULT_CURATED_LIMIT
    ): GetQueryPhotosResponseBody

    @GET("collections/featured")
    suspend fun getFeaturedCollections(
        @Query("per_page") pageSize: Int = DEFAULT_FEATURED_NUMBER
    ): QueryCollectionsDto

    @GET("photos/{id}")
    suspend fun getPhotoById(
        @Path("id") id: Int
    ): PhotoDto

    private companion object {
        private const val QUERY_PARAM_QUERY = "query"
        private const val QUERY_PARAM_PAGE = "page"
        private const val QUERY_PARAM_PER_PAGE = "per_page"
    }
}