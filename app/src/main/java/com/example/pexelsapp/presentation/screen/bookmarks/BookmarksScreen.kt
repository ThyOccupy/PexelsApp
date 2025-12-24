package com.example.pexelsapp.presentation.screen.bookmarks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pexelsapp.presentation.common.components.photo.PhotoGrid
import com.example.pexelsapp.presentation.model.PhotoUiEntity

@Composable
fun BookmarksScreen(
    viewModel: BookmarksScreenViewModel = hiltViewModel(),
    onNavigateClick: (PhotoUiEntity) -> Unit
) {

    val bookmarks = viewModel.bookmarks.collectAsLazyPagingItems()

    BookmarksScreenLayout(
        photos = bookmarks,
        onNavigateClick = {photo ->
            onNavigateClick (photo)
        }
    )

}

@Composable
fun BookmarksScreenLayout(
    photos: LazyPagingItems<PhotoUiEntity>,
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
            text = "Bookmarks",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(vertical = 25.dp)
        )

        PhotoGrid (
            photosList = photos,
            onExploreClick = {  },
            onRetryClick = { },
            onPhotoClick = {photo -> onNavigateClick(photo) },
            isBookmarkScreen = true
        )

    }
}