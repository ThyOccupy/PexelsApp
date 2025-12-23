package com.example.pexelsapp.data.remote.dto


import com.squareup.moshi.Json

data class QueryCollectionsDto(
    @Json(name = "collections")
    val collection: List<GetCollectionResponseBody>
)
