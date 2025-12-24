package com.example.pexelsapp.presentation.events

import com.example.pexelsapp.presentation.model.PhotoUiEntity

sealed class DetailsScreenEvent {
    data class InitPhotoApi(val photoId: Int) : DetailsScreenEvent()
    data class InitPhotoDb(val photoId: Int) : DetailsScreenEvent()
    data class OnDownloadClicked(val photo: PhotoUiEntity): DetailsScreenEvent()
    data class OnBookmarkClicked(val photo: PhotoUiEntity): DetailsScreenEvent()
}