package com.example.pexelsapp.presentation.model

import com.example.pexelsapp.domain.model.PhotoModel
import kotlin.Int

data class PhotoUiEntity (
    val id: Int,
    val url: String,
    val width: Int,
    val height: Int,
    val photographer: String,
    val isBookmarked: Boolean
)

fun PhotoModel.toUiEntity(): PhotoUiEntity = PhotoUiEntity(
    id = id,
    url = urlOrig,
    width = width,
    height = height,
    photographer = photographer,
    isBookmarked = isBookmarked
)