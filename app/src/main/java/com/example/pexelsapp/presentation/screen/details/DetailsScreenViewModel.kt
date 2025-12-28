package com.example.pexelsapp.presentation.screen.details

import androidx.lifecycle.viewModelScope
import com.example.pexelsapp.domain.usecase.interfaces.DownloadImageUseCase
import com.example.pexelsapp.domain.usecase.interfaces.GetPhotoByIdApiUseCase
import com.example.pexelsapp.domain.usecase.interfaces.GetPhotoByIdDbUseCase
import com.example.pexelsapp.domain.usecase.interfaces.SwitchBookmarkStatusUseCase
import com.example.pexelsapp.presentation.model.PhotoUiEntity
import com.example.pexelsapp.presentation.events.DetailsScreenEvent
import com.example.pexelsapp.presentation.model.toUiEntity
import com.example.pexelsapp.presentation.screen.root.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val getPhotoByIdApi: GetPhotoByIdApiUseCase,
    private val getPhotoByIdDb: GetPhotoByIdDbUseCase,
    private val downloadImageUseCase: DownloadImageUseCase,
    private val switchBookmarkStatusUseCase: SwitchBookmarkStatusUseCase
) : BaseViewModel() {

    private val _photoModel = MutableStateFlow<PhotoUiEntity?>(null)
    val photoModel: StateFlow<PhotoUiEntity?> = _photoModel

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading


    fun initDataDb(id: Int) {
        viewModelScope.launch(errorHandler) {
            _isLoading.value = true
            _photoModel.value = null
            try {
                getPhotoByIdDb.execute(id).collect { data ->
                    _photoModel.value = data.toUiEntity()
                    _isLoading.value = false
                    _errorState.value = null
                }
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun initDataApi(id: Int) {
        viewModelScope.launch(errorHandler) {
            _isLoading.value = true
            _photoModel.value = null

            try {
                getPhotoByIdApi.execute(id).collect { data ->
                    _photoModel.value = data.toUiEntity()
                    _isLoading.value = false
                    _errorState.value = null
                }
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun onEvent(event: DetailsScreenEvent) {
        when (event) {
            is DetailsScreenEvent.InitPhotoApi -> {
                initDataApi(event.photoId)
            }

            is DetailsScreenEvent.InitPhotoDb -> {
                initDataDb(event.photoId)
            }

            is DetailsScreenEvent.OnDownloadClicked -> {
                downloadPhoto(event.photo)
            }

            is DetailsScreenEvent.OnBookmarkClicked -> {
                switchBookmarkStatus(event.photo)
            }
        }
    }

    private fun downloadPhoto(photo: PhotoUiEntity) {
        viewModelScope.launch(errorHandler) {
                downloadImageUseCase.execute(photo.url, photo.id)
        }
    }

    private fun switchBookmarkStatus(photo: PhotoUiEntity) {
        viewModelScope.launch(errorHandler) {
                val isBookmarked = !photo.isBookmarked
                switchBookmarkStatusUseCase.execute(photo.id, isBookmarked)
        }
    }
}