package com.example.pexelsapp.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.pexelsapp.MainViewModel
import com.example.pexelsapp.ui.components.BottomBar
import com.example.pexelsapp.ui.navigation.NavGraph
import com.example.pexelsapp.ui.navigation.rememberNavigationState

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val navigationState = rememberNavigationState()

    Scaffold(
        bottomBar = { BottomBar(navigationState) }
    ) { paddingValues ->

        NavGraph(
            navHostController = navigationState.navHostController,
            homeScreenContent = { HomeScreen(viewModel) },
            bookmarkScreenContent = { Text(text = "BookmarkScreen", color = Color.Black) },
            modifier = Modifier.padding(paddingValues)
        )
    }
}