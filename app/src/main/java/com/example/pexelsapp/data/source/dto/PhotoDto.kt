package com.example.pexelsapp.data.source.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "src")
    val urls: PhotoUrlDto,
    @Json(name = "width")
    val width: Int,
    @Json(name = "height")
    val height: Int,
    @Json(name = "photographer")
    val photographer: String
)
