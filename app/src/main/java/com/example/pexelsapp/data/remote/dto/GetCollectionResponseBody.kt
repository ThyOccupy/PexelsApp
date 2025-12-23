package com.example.pexelsapp.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetCollectionResponseBody(
    @Json(name = "title")
    val title: String
)
