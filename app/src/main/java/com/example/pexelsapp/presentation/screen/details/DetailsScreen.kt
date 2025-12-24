import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pexelsapp.R
import com.example.pexelsapp.presentation.common.components.photo.PhotoCard
import com.example.pexelsapp.presentation.common.components.button.BackButton
import com.example.pexelsapp.presentation.common.components.button.BookmarkButton
import com.example.pexelsapp.presentation.common.components.button.DownloadButton
import com.example.pexelsapp.presentation.common.components.toolbar.ProgressBar
import com.example.pexelsapp.presentation.common.drawable.PexelsIcons
import com.example.pexelsapp.presentation.common.navigation.Screen
import com.example.pexelsapp.presentation.events.DetailsScreenEvent
import com.example.pexelsapp.presentation.model.PhotoUiEntity
import com.example.pexelsapp.presentation.screen.details.DetailsScreenViewModel

@Composable
fun DetailsScreen(
    photoId: Int,
    route: String,
    viewModel: DetailsScreenViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
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

    if (photoModel != null) {
        DetailsScreenLayout(
            isLoadingData = isLoadingData,
            photoModel = photoModel!!,
            onBackPressed = onBackPressed,
            onDownloadClicked = {
                viewModel.onEvent(DetailsScreenEvent.OnDownloadClicked(it))
            }
        )
    } else {
        Stub(isLoadingData)
    }
}


@Composable
fun DetailsScreenLayout(
    isLoadingData: Boolean,
    photoModel: PhotoUiEntity,
    onBackPressed: () -> Unit,
    onDownloadClicked: (PhotoUiEntity) -> Unit
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
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                PhotoCard(
                    modifier = Modifier
                        .padding(24.dp),
                    photo = photoModel,
                ) {
                }
            }
            item {
                DetailedBar(
                    isBookmarked = photoModel.isBookmarked,
                    onDownloadClicked = {
                        onDownloadClicked(photoModel)
                    }
                )
            }
        }
    }

}

@Composable
fun Stub(isLoading: Boolean) {
    ProgressBar(
        isLoading = isLoading
    )
}


@Composable
fun DetailedBar(
    isBookmarked: Boolean,
    onDownloadClicked: () -> Unit
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
        ) {}
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