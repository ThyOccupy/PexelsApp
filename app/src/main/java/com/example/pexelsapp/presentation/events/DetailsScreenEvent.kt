package com.example.pexelsapp.presentation.events

sealed class DetailsScreenEvent {
    data class InitPhotoApi(val photoId: Int) : DetailsScreenEvent()
    data class InitPhotoDb(val photoId: Int) : DetailsScreenEvent()
}