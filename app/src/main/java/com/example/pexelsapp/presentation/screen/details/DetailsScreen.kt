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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pexelsapp.R
import com.example.pexelsapp.presentation.model.PhotoUiEntity
import com.example.pexelsapp.presentation.common.components.photo.PhotoCard
import com.example.pexelsapp.presentation.common.components.button.BackButton
import com.example.pexelsapp.presentation.common.components.button.BookmarkButton
import com.example.pexelsapp.presentation.common.components.button.DownloadButton
import com.example.pexelsapp.presentation.events.DetailsScreenEvent
import com.example.pexelsapp.presentation.screen.details.DetailsScreenViewModel

@Composable
fun DetailsScreen(
    photoId: Int?,
    viewModel: DetailsScreenViewModel = hiltViewModel(),
        onBackPressed: () -> Unit
) {

    photoId?.let {
        DetailsScreenEvent.onInitEvent(it)
    }?.let {
        viewModel.onEvent(it)
    } ?: Stub()

    val photoModel = viewModel.photoModel.collectAsState().value
    photoModel?.let{
        DetailsScreenLayout(
            photoModel = photoModel,
            onBackPressed = onBackPressed
        )
    }
}

@Composable
fun DetailsScreenLayout(
    photoModel: PhotoUiEntity,
    onBackPressed: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(MaterialTheme.colorScheme.background),

    ) {
        TopDetailsBar(
            photographerName = photoModel.photographer,
            onBackPressed = onBackPressed
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                PhotoCard(
                    photo = photoModel,
                ) {
                }
            }
            item {
                DetailedBar()
            }
        }
    }

}

@Composable
fun Stub() {
Text("Stub is opened")
}





@Composable
fun DetailedBar () {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        DownloadButton(
            title = stringResource(R.string.download),
            icon = painterResource(R.drawable.ic_download),
            onClick = {}
        )

        Spacer(Modifier.weight(1f))

        BookmarkButton {}
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
            icon = painterResource(R.drawable.ic_back),
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