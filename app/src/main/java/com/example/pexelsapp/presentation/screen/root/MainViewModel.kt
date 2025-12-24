package com.example.pexelsapp.presentation.screen.root

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.pexelsapp.domain.usecase.interfaces.GetHeaderUseCase
import com.example.pexelsapp.domain.usecase.interfaces.GetPhotosUseCase
import com.example.pexelsapp.presentation.model.HeaderUiEntity
import com.example.pexelsapp.presentation.model.PhotoUiEntity
import com.example.pexelsapp.presentation.events.HomeScreenEvent
import com.example.pexelsapp.presentation.model.toHeaderUiEntity
import com.example.pexelsapp.presentation.model.toUiEntity
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

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> get() = _query

    private val _photos =
        MutableStateFlow<PagingData<PhotoUiEntity>>(PagingData.Companion.empty())
    val photos: StateFlow<PagingData<PhotoUiEntity>> get() = _photos

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
            is HomeScreenEvent.onRetryClicked -> initialPhotos()
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
                    }.cachedIn(viewModelScope)
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