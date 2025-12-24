package com.example.pexelsapp.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pexelsapp.ui.components.BottomBar
import com.example.pexelsapp.ui.navigation.NavGraph
import com.example.pexelsapp.ui.navigation.rememberNavigationState

@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()

    Scaffold(
        bottomBar = {
            Box(modifier = Modifier.height(64.dp)) {
                BottomBar(navigationState)
            }
        }
    ) { paddingValues ->

        NavGraph(
            navHostController = navigationState.navHostController,
            imagesHeadersScreenContent = { HomeScreen{photo ->
                navigationState.navigateToDetails(photo)
            } },
            bookmarkScreenContent = { Text(text = "BookmarkScreen", color = Color.Black) },
            detailsScreenContent = { Text(text = "DetailsScreen id: $it", color = Color.Black) },
            modifier = Modifier.padding(paddingValues)
        )
    }
}