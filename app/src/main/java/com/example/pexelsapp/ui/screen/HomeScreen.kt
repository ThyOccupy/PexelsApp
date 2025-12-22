package com.example.pexelsapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.pexelsapp.MainViewModel
import com.example.pexelsapp.ui.components.Headers
import com.example.pexelsapp.ui.components.ImagesGrid
import com.example.pexelsapp.ui.components.SearchBar

@Composable
fun HomeScreen(viewModel: MainViewModel){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .systemBarsPadding()
    ) {

        val headersState = viewModel.headersState.observeAsState(listOf())
        val photosState = viewModel.photosState.observeAsState(listOf())

        var query by remember {
            mutableStateOf("")
        }

        SearchBar(
            query = query,
            onQueryChange = { newValue ->
                query = newValue
            }
        )

        Headers(list = headersState.value){}

        ImagesGrid(photosList = photosState.value){}

    }
}