package com.example.pexelsapp.ui

import com.example.pexelsapp.domain.model.PhotoModel

data class PhotoUiEntity (
    val url: String,
    val width: Int,
    val height: Int,
    val photographer: String
)

fun PhotoModel.toUiEntity(): PhotoUiEntity = PhotoUiEntity(
    url = urlOrig,
    width = width,
    height = height,
    photographer = photographer
)