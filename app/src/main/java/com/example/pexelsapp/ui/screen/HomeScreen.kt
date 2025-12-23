package com.example.pexelsapp.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pexelsapp.MainViewModel
import com.example.pexelsapp.ui.HeaderUiEntity
import com.example.pexelsapp.ui.components.Headers
import com.example.pexelsapp.ui.components.ImagesGrid
import com.example.pexelsapp.ui.components.SearchBar

@Composable
fun HomeScreen(viewModel: MainViewModel = hiltViewModel()){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .systemBarsPadding()
    ) {

        val photos = viewModel.photos.collectAsLazyPagingItems()


        var query by remember {
            mutableStateOf("Christmas")
        }

        SearchBar(
            query = query,
            onQueryChange = { newValue ->
                query = newValue
            }
        )

        val headers = listOf(
            HeaderUiEntity("Ice", true),
            HeaderUiEntity("Watches", false),
            HeaderUiEntity("Drawing", true),
            HeaderUiEntity("Brick", false),
            HeaderUiEntity("Architecture", false),
        )

        Headers(list = headers){}

        PhotoLoadErrorToast(photos.loadState)

        ImagesGrid(photosList = photos){}

    }
}
@Composable
fun PhotoLoadErrorToast(loadState: CombinedLoadStates) {
    val context = LocalContext.current
    LaunchedEffect(key1 = loadState) {
        if (loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error:" + (loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}