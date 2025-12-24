package com.example.pexelsapp.presentation.screen.root

import DetailsScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pexelsapp.presentation.common.navigation.NavigationBar
import com.example.pexelsapp.presentation.common.navigation.NavGraph
import com.example.pexelsapp.presentation.common.navigation.rememberNavigationState
import com.example.pexelsapp.presentation.screen.home.HomeScreen

@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()

    Scaffold(
        bottomBar = {
            Box(modifier = Modifier.height(64.dp)) {
                NavigationBar(navigationState)
            }
        }
    ) { paddingValues ->

        NavGraph(
            navHostController = navigationState.navHostController,
            imagesHeadersScreenContent = {
                HomeScreen { photo ->
                    val navBackStack = navigationState.navHostController.currentBackStackEntry
                    val route = navBackStack?.destination?.route ?: throw IllegalStateException()
                    navigationState.navigateToDetails(photo, route)
                }
            },
            bookmarkScreenContent = { Text(text = "BookmarkScreen", color = Color.Black) },
            detailsScreenContent = {id,route ->
                DetailsScreen(
                    photoId = id,
                    route = route,
                    onBackPressed = {
                    navigationState.goBack()
                    },
                    onExploreClick = {
                        navigationState.navigateTo(route)
                    }
                )
            },
            modifier = Modifier.padding(paddingValues)
        )
    }
}