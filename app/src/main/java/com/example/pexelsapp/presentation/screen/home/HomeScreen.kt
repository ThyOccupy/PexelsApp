package com.example.pexelsapp.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pexelsapp.R
import com.example.pexelsapp.presentation.model.PhotoUiEntity
import com.example.pexelsapp.presentation.common.components.toolbar.Headers
import com.example.pexelsapp.presentation.common.components.photo.PhotoGrid
import com.example.pexelsapp.presentation.common.components.toolbar.ProgressBar
import com.example.pexelsapp.presentation.common.components.toolbar.SearchBar
import com.example.pexelsapp.presentation.events.HomeScreenEvent

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    onPhotoClick: (PhotoUiEntity) -> Unit
) {
    val errorState = viewModel.errorState.collectAsState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .systemBarsPadding()
        ) {

            val photos = viewModel.photos.collectAsLazyPagingItems()
            val headers = viewModel.titles.collectAsState().value


            var query by remember {
                mutableStateOf("")
            }

            SearchBar(
                query = query,
                onQueryChange = { newValue ->
                    query = newValue
                    viewModel.onEvent(HomeScreenEvent.OnSearchQueryChange(newValue))
                }
            )
            Headers(
                headers = headers
            ) { header ->
                query = header.name
                viewModel.checkAndMoveTitle(query)
                viewModel.onEvent(HomeScreenEvent.OnSearchQueryChange(query))
            }
            ProgressBar(
                isLoading = photos.loadState.refresh is LoadState.Loading
            )
            PhotoGrid(
                photosList = photos,
                errorMessage = stringResource(R.string.no_results_found),
                errorResId = errorState.value,
                onPhotoClick = { onPhotoClick(it) },
                onExploreClick = {
                    viewModel.onEvent(HomeScreenEvent.OnExploreClicked)
                },
                onRetryClick = {
                    viewModel.onEvent(HomeScreenEvent.OnRetryClicked)
                }
            )

        }
    }