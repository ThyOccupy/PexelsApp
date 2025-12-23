package com.example.pexelsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.example.pexelsapp.domain.usecase.GetHeaderUseCase
import com.example.pexelsapp.domain.usecase.GetPhotosUseCase
import com.example.pexelsapp.ui.HeaderUiEntity
import com.example.pexelsapp.ui.events.HomeScreenEvent
import com.example.pexelsapp.ui.toHeaderUiEntity
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
    private val getPhotosUseCase: GetPhotosUseCase,
    private val getHeaderUseCase: GetHeaderUseCase
) : ViewModel() {

    private val _query = MutableStateFlow("Christmas")
    val query: StateFlow<String> get() = _query

    private val _photos =
        MutableStateFlow<PagingData<com.example.pexelsapp.ui.PhotoUiEntity>>(PagingData.empty())
    val photos: StateFlow<PagingData<com.example.pexelsapp.ui.PhotoUiEntity>> get() = _photos

    private val _titles = MutableStateFlow<List<HeaderUiEntity>>(emptyList())
    val titles: StateFlow<List<HeaderUiEntity>> get() = _titles


    init {
        initialPhotos()
        initialTitles()
    }

    fun onEvent(event: HomeScreenEvent) {
        when(event){
            is HomeScreenEvent.OnSearchQueryChange -> setQuery(event.query)
            is HomeScreenEvent.OnExploreClicked -> initialPhotos()
        }
    }

    private fun setQuery(newQuery: String) {
        _query.value = newQuery
    }

    private fun initialPhotos() {
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

    private fun initialTitles() {
        viewModelScope.launch {
            val headers = getHeaderUseCase.execute().map {
                it.toHeaderUiEntity()
            }
            _titles.value = headers
        }
    }
}