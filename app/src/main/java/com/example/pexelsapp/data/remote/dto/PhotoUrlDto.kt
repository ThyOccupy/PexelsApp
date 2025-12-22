package com.example.pexelsapp.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoUrlDto(
    @Json(name = "original")
    val original: String,
    @Json(name = "large2x")
    val compressed: String
)
