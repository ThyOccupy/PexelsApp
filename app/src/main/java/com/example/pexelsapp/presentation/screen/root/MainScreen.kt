package com.example.pexelsapp.presentation.screen.root

import DetailsScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pexelsapp.presentation.navigation.NavigationBar
import com.example.pexelsapp.presentation.navigation.NavGraph
import com.example.pexelsapp.presentation.navigation.rememberNavigationState
import com.example.pexelsapp.presentation.screen.bookmarks.BookmarksScreen
import com.example.pexelsapp.presentation.screen.home.HomeScreen

@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
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
            bookmarkScreenContent = {
                BookmarksScreen(
                    onPhotoClick = {photo ->
                        val navBackStack = navigationState.navHostController.currentBackStackEntry
                        val route = navBackStack?.destination?.route ?: throw IllegalStateException()
                        navigationState.navigateToDetails(photo, route)
                    },
                    onExploreClick = {
                        navigationState.navigateToHome()
                    }
                )
            },
            detailsScreenContent = { id, route ->
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
            }, modifier = Modifier.padding(paddingValues)
        )
    }
}