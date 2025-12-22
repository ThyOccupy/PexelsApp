package com.example.pexelsapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pexelsapp.ui.components.Headers
import com.example.pexelsapp.ui.components.ImagesGrid
import com.example.pexelsapp.ui.components.PhotoUI
import com.example.pexelsapp.ui.components.Sample
import com.example.pexelsapp.ui.components.SearchBar

@Preview
@Composable
fun HomeScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .systemBarsPadding()
    ) {

        var query by remember {
            mutableStateOf("")
        }

        SearchBar(
            query = query,
            onQueryChange = { newValue ->
                query = newValue
            }
        )

        val listOfTopics = listOf(
            Sample ("Ice", true),
            Sample ("Watches", false),
            Sample ("Drawing", false),
            Sample ("Brick", false),
            Sample ("Architecture", false),
        )

        Headers(list = listOfTopics) {

        }

        val listOfPhotos = listOf(
            PhotoUI(
                url = "https://images.pexels.com/photos/716658/pexels-photo-716658.jpeg",
                width = 6100,
                height = 4067,
                photographer = "Andrea Piacquadio"
            ),
            PhotoUI(
                url = "https://images.pexels.com/photos/3444345/pexels-photo-3444345.png",
                width = 4000,
                height = 6000,
                photographer = "Kristina Paukshtite"
            ),
            PhotoUI(
                url = "https://images.pexels.com/photos/5728187/pexels-photo-5728187.jpeg",
                width = 6000,
                height = 3783,
                photographer = "Any Lane"
            ),
            PhotoUI(
                url = "https://images.pexels.com/photos/1303094/pexels-photo-1303094.jpeg",
                width =  5616,
                height = 3744,
                photographer = "George Dolgikh"
            )
        )

        ImagesGrid(photosList = listOfPhotos)

    }
}