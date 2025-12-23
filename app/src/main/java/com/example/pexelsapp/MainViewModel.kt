package com.example.pexelsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.example.pexelsapp.domain.usecase.GetPhotosUseCase
import com.example.pexelsapp.ui.toUiEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase
) : ViewModel() {

    /*private val photos = listOf<PhotoUiEntity>(
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
    )*/

    private val _query = MutableStateFlow("Christmas")
    val query: StateFlow<String> get() = _query

    private val _photos = MutableStateFlow<PagingData<com.example.pexelsapp.ui.PhotoUiEntity>>(PagingData.empty())
    val photos: StateFlow<PagingData<com.example.pexelsapp.ui.PhotoUiEntity>> get() = _photos

    init {
        observePhotos()
    }

    fun setQuery(newQuery: String) {
        _query.value = newQuery
    }

    private fun observePhotos() {
        viewModelScope.launch {
            query.collectLatest { queryValue ->
                getPhotosUseCase.execute(queryValue)
                    .map { pagingData ->
                        pagingData.map { photoModel ->
                            photoModel.toUiEntity()
                        }
                    }
                    .collectLatest { transformedPagingData ->
                        _photos.value = transformedPagingData
                    }
            }
        }
    }
}