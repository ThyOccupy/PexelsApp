package com.example.pexelsapp.presentation.screen.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.pexelsapp.domain.usecase.interfaces.GetBookmarksUseCase
import com.example.pexelsapp.presentation.model.PhotoUiEntity
import com.example.pexelsapp.presentation.model.toUiEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksScreenViewModel @Inject constructor(
    private val getBookmarksUseCase: GetBookmarksUseCase
): ViewModel(){
    private val _bookmarks = MutableStateFlow<PagingData<PhotoUiEntity>>(PagingData.empty())
    val bookmarks: StateFlow<PagingData<PhotoUiEntity>> = _bookmarks

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        getBookmarks()
    }

    private fun getBookmarks() {
        viewModelScope.launch {
            getBookmarksUseCase.execute()
                .map { pagingData ->
                    pagingData.map { photoModel ->
                        photoModel.toUiEntity()
                    }
                }.cachedIn(viewModelScope)

                .collectLatest { transformedData ->
                    _bookmarks.value = transformedData
                    _isLoading.value = false
                }
        }
    }
}