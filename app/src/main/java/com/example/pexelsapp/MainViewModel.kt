package com.example.pexelsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pexelsapp.domain.HeaderItemEntity
import com.example.pexelsapp.domain.PhotoUiEntity

class MainViewModel() : ViewModel() {

    private val headers = listOf(
        HeaderItemEntity("Ice", true),
        HeaderItemEntity("Watches", false),
        HeaderItemEntity("Drawing", false),
        HeaderItemEntity("Brick", false),
        HeaderItemEntity("Architecture", false),
    )

    private val photos = listOf<PhotoUiEntity>(
        PhotoUiEntity(
            url = "https://images.pexels.com/photos/716658/pexels-photo-716658.jpeg",
            width = 6100,
            height = 4067,
            photographer = "Andrea Piacquadio"
        ),
        PhotoUiEntity(
            url = "https://images.pexels.com/photos/3444345/pexels-photo-3444345.png",
            width = 4000,
            height = 6000,
            photographer = "Kristina Paukshtite"
        ),
        PhotoUiEntity(
            url = "https://images.pexels.com/photos/5728187/pexels-photo-5728187.jpeg",
            width = 6000,
            height = 3783,
            photographer = "Any Lane"
        ),
        PhotoUiEntity(
            url = "https://images.pexels.com/photos/1303094/pexels-photo-1303094.jpeg",
            width = 5616,
            height = 3744,
            photographer = "George Dolgikh"
        )
    )

    private val _headersState = MutableLiveData(headers)
    val headersState: LiveData<List<HeaderItemEntity>> = _headersState

    private val _photosState = MutableLiveData(photos)
    val photosState: LiveData<List<PhotoUiEntity>> = _photosState

}