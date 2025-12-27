package com.example.pexelsapp.presentation.screen.bookmarks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pexelsapp.R
import com.example.pexelsapp.presentation.common.components.photo.PhotoGrid
import com.example.pexelsapp.presentation.common.components.toolbar.ProgressBar
import com.example.pexelsapp.presentation.model.PhotoUiEntity

@Composable
fun BookmarksScreen(
    viewModel: BookmarksScreenViewModel = hiltViewModel(),
    onPhotoClick: (PhotoUiEntity) -> Unit,
    onExploreClick: () -> Unit
) {
    val errorState = viewModel.errorState.collectAsState()
    val bookmarks = viewModel.bookmarks.collectAsLazyPagingItems()
    val isLoading by viewModel.isLoading.collectAsState()

    BookmarksScreenLayout(
        photos = bookmarks,
        isLoading = isLoading,
        errorResId = errorState.value,
        onNavigateClick = {photo ->
            onPhotoClick (photo)
        },
        onExploreClick = onExploreClick
    )

}

@Composable
fun BookmarksScreenLayout(
    photos: LazyPagingItems<PhotoUiEntity>,
    isLoading: Boolean,
    errorResId: Int?,
    onExploreClick: () -> Unit,
    onNavigateClick: (PhotoUiEntity) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.bookmarks),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(vertical = 25.dp)
        )

        ProgressBar(isLoading)

        PhotoGrid (
            photosList = photos,
            errorMessage = stringResource(R.string.no_bookmark_found),
            errorResId = errorResId,
            onExploreClick = onExploreClick,
            onRetryClick = { },
            onPhotoClick = {photo -> onNavigateClick(photo) },
            isBookmarkScreen = true
        )

    }
}