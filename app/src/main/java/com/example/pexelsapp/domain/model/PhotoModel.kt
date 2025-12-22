package com.example.pexelsapp.domain.model

data class PhotoModel(
    val id: Long,
    val urlOrig: String,
    val urlComp: String,
    val width: Int,
    val height: Int,
    val photographer: String,
    val isBookmarked: Boolean
)