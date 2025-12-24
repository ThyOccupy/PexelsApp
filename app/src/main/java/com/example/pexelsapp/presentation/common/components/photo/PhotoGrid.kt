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
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

@Composable
fun PhotoGrid(
    photosList: LazyPagingItems<PhotoUiEntity>,
    errorMessage: String,
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
            onExploreClick = onExploreClick,
            onRetryClick = onRetryClick
        )
    }
}


@Composable
fun NoResultsStub(
    photosList: LazyPagingItems<PhotoUiEntity>,
    errorMessage: String,
    onExploreClick: () -> Unit,
    onRetryClick: () -> Unit
) {
    when {
        photosList.loadState.refresh is LoadState.NotLoading && photosList.itemCount == 0 -> {

            val context = LocalContext.current
            LaunchedEffect(key1 = photosList.loadState) {
                if (photosList.loadState.refresh is LoadState.Error) {
                    val error = (photosList.loadState.refresh as LoadState.Error).error.apply {
                        when (this) {
                            is HttpException -> when (this.code()) {
                                400 -> R.string.error_400
                                401 -> R.string.error_400
                                403 -> R.string.error_400
                                404 -> R.string.error_400
                                429 -> R.string.error_400
                                500 -> R.string.error_400
                                502 -> R.string.error_400
                                else -> "${R.string.failed_to_retrieve_data_error}${this.message}"
                            }

                            is UnknownHostException -> R.string.error_unknown_host
                            is ConnectException -> R.string.error_connect
                            is SocketTimeoutException -> R.string.error_timeout
                            else -> {
                                "${R.string.unexpected_error}${this.message}"
                            }
                        }
                    }

                    Toast.makeText(context, "${R.string.toast_error}$error", Toast.LENGTH_LONG).show()
                }
            }

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