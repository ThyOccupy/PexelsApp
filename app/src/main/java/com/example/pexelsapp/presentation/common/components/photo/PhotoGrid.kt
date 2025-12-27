package com.example.pexelsapp.presentation.common.components.photo

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.pexelsapp.R
import com.example.pexelsapp.presentation.common.components.button.StubButton
import com.example.pexelsapp.presentation.common.drawable.PexelsIcons
import com.example.pexelsapp.presentation.model.PhotoUiEntity

@Composable
fun PhotoGrid(
    photosList: LazyPagingItems<PhotoUiEntity>,
    errorMessage: String,
    errorResId: Int? = null,
    isBookmarkScreen: Boolean = false,
    onPhotoClick: (PhotoUiEntity) -> Unit,
    onExploreClick: () -> Unit,
    onRetryClick: () -> Unit
) {
    Box(modifier = Modifier) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(photosList.itemCount) { index ->
                photosList[index]?.let { photo ->
                    PhotoCard(
                        modifier = Modifier
                            .padding(12.dp),
                        photo = photo,
                        isBookmarkScreen = isBookmarkScreen
                    ) {
                        onPhotoClick(it)
                    }
                }
            }
        }
        NoResultsStub(
            photosList = photosList,
            errorMessage = errorMessage,
            errorResId = errorResId,
            onExploreClick = onExploreClick,
            onRetryClick = onRetryClick
        )
    }
}


@Composable
fun NoResultsStub(
    photosList: LazyPagingItems<PhotoUiEntity>,
    errorMessage: String,
    errorResId: Int?,
    onExploreClick: () -> Unit,
    onRetryClick: () -> Unit
) {

    if (errorResId != null) {
        val context = LocalContext.current
        val errorText =  stringResource(errorResId)
        LaunchedEffect(key1 = photosList.loadState) {
            Toast.makeText(
                context,
                errorText,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    when {
        photosList.loadState.refresh
                is LoadState.NotLoading && photosList.itemCount == 0 -> {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = errorMessage)
                Spacer(Modifier.height(12.dp))
                StubButton(text = stringResource(R.string.explore), onClick = onExploreClick)
            }
        }

        photosList.loadState.refresh is LoadState.Error -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = PexelsIcons.NoNetwork,
                    contentDescription = null
                )
                Spacer(Modifier.height(12.dp))
                StubButton(
                    text = stringResource(R.string.try_again),
                    onClick = onRetryClick
                )
            }
        }
    }
}