package com.example.pexelsapp.data

import com.example.pexelsapp.data.database.entities.PexelsEntity
import com.example.pexelsapp.data.source.dto.PhotoDto
import com.example.pexelsapp.domain.model.PhotoModel

fun PhotoDto.toEntity() : PexelsEntity = PexelsEntity(
        id = id,
        width = width,
        height = height,
        photographer = photographer,
        urlOrig = urls.original,
        urlComp = urls.compressed,
        isBookmarked = false
    )

fun PexelsEntity.toModel() : PhotoModel = PhotoModel(
        id = id,
        width = width,
        height = height,
        photographer = photographer,
        urlComp = urlComp,
        urlOrig = urlOrig,
        isBookmarked = isBookmarked
    )
