import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.pexelsapp.R
import com.example.pexelsapp.presentation.common.components.button.BackButton
import com.example.pexelsapp.presentation.common.components.button.BookmarkButton
import com.example.pexelsapp.presentation.common.components.button.DownloadButton
import com.example.pexelsapp.presentation.common.components.photo.ZoomablePhoto
import com.example.pexelsapp.presentation.common.components.toolbar.ProgressBar
import com.example.pexelsapp.presentation.common.drawable.PexelsIcons
import com.example.pexelsapp.presentation.navigation.Screen
import com.example.pexelsapp.presentation.events.DetailsScreenEvent
import com.example.pexelsapp.presentation.model.PhotoUiEntity
import com.example.pexelsapp.presentation.screen.details.DetailsScreenViewModel

@Composable
fun DetailsScreen(
    photoId: Int,
    route: String,
    viewModel: DetailsScreenViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
    onExploreClick: () -> Unit
) {

    val errorState = viewModel.errorState.collectAsState()
    val errorResId = errorState.value
    if (errorResId != null) {
        val context = LocalContext.current
        val errorText = stringResource(errorResId)
        LaunchedEffect(key1 = photoId) {
            Toast.makeText(
                context,
                errorText,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    LaunchedEffect(photoId, route) {
        when (route) {
            Screen.NestedHome.route -> {
                val initialEvent = DetailsScreenEvent.InitPhotoApi(photoId)
                viewModel.onEvent(initialEvent)
            }

            Screen.Bookmark.route -> {
                val initialEvent = DetailsScreenEvent.InitPhotoDb(photoId)
                viewModel.onEvent(initialEvent)
            }
        }
    }

    val photoModel by viewModel.photoModel.collectAsState()
    val isLoadingData by viewModel.isLoading.collectAsState()

    val currentPhoto = photoModel

    if (currentPhoto != null) {
        DetailsScreenLayout(
            isLoadingData = isLoadingData,
            photoModel = currentPhoto,
            onBackPressed = onBackPressed,
            onDownloadClicked = {
                viewModel.onEvent(DetailsScreenEvent.OnDownloadClicked(it))
            },
            onBookmarkClicked = {
                viewModel.onEvent(DetailsScreenEvent.OnBookmarkClicked(it))
            }
        )
    } else {
        Stub(
            onBackPressed = onBackPressed,
            onExploreClick = onExploreClick
        )
    }
}


@Composable
fun DetailsScreenLayout(
    isLoadingData: Boolean,
    photoModel: PhotoUiEntity,
    onBackPressed: () -> Unit,
    onDownloadClicked: (PhotoUiEntity) -> Unit,
    onBookmarkClicked: (PhotoUiEntity) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopDetailsBar(
            photographerName = photoModel.photographer,
            onBackPressed = onBackPressed
        )
        ProgressBar(
            isLoading = isLoadingData
        )
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ZoomablePhoto(
                photo = photoModel,
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, top = 12.dp)
            )
            DetailedBar(
                isBookmarked = photoModel.isBookmarked,
                onDownloadClicked = {
                    onDownloadClicked(photoModel)
                },
                onBookmarkClicked = {
                    onBookmarkClicked(photoModel)
                }
            )
        }
    }
}

@Composable
fun Stub(
    onBackPressed: () -> Unit,
    onExploreClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopDetailsBar(
            photographerName = "",
            onBackPressed = onBackPressed
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.background
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.image_not_found),
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                modifier = Modifier
                    .clickable { onExploreClick() },
                text = stringResource(R.string.explore),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary
            )
        }

    }
}


@Composable
fun DetailedBar(
    isBookmarked: Boolean,
    onDownloadClicked: () -> Unit,
    onBookmarkClicked: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        DownloadButton(
            title = stringResource(R.string.download),
            icon = PexelsIcons.Download,
            onClick = onDownloadClicked
        )

        Spacer(Modifier.weight(1f))

        BookmarkButton(
            isBookmarked = isBookmarked,
            onBookmarkClicked = onBookmarkClicked
        )
    }
}

@Composable
fun TopDetailsBar(
    photographerName: String,
    onBackPressed: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.background
            )
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {
        BackButton(
            icon = PexelsIcons.Back,
            onBackPressed = onBackPressed
        )
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = photographerName,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground

        )
    }


}