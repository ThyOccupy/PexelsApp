package com.example.pexelsapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.example.pexelsapp.R
import com.example.pexelsapp.ui.PhotoUiEntity

@Composable
fun ImagesGrid(
    photosList: LazyPagingItems<PhotoUiEntity>,
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
                    PhotoCard(photo = photo) {}
                    onPhotoClick(photo)
                }
            }
        }
        NoResultsStub(
            photosList = photosList,
            onExploreClick = onExploreClick,
            onRetryClick = onRetryClick
        )
    }
}

@Composable
fun PhotoCard(
    photo: PhotoUiEntity,
    onclick: () -> Unit
) {
    val ratio = countSize(photo.width, photo.height)

    Box(
        modifier = Modifier
            .aspectRatio(ratio)
            .fillMaxWidth()
            .clickable { onclick() }
            .padding(12.dp)
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(20.dp)
            )
    ) {

        AsyncImage(
            model = photo.url,
            contentDescription = photo.photographer,
            modifier = Modifier
                .fillMaxSize()
                .clip(
                    shape = RoundedCornerShape(20.dp)
                ),
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
        )
    }
}

private fun countSize(width: Int, height: Int): Float {
    return width.toFloat() / height
}

@Composable
fun NoResultsStub(
    photosList: LazyPagingItems<PhotoUiEntity>,
    onExploreClick: () -> Unit,
    onRetryClick: () -> Unit
) {
    when {
        photosList.loadState.refresh is LoadState.NotLoading && photosList.itemCount == 0 -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(R.string.no_results_found))
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
                    painter = painterResource(id = R.drawable.ic_internet_off),
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