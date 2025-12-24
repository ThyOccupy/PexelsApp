package com.example.pexelsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.pexelsapp.ui.PhotoUiEntity

@Composable
fun NavGraph(
    navHostController: NavHostController,
    imagesHeadersScreenContent: @Composable () -> Unit,
    bookmarkScreenContent: @Composable () -> Unit,
    detailsScreenContent: @Composable (photoId: Int) -> Unit,
    modifier: Modifier = Modifier
    ) {

    //Store app navGraph
    NavHost(
        navController = navHostController,
        startDestination = Screen.NestedHome.route
    ){
        homeScreenNavGraph(
            imagesHeadersScreenContent = imagesHeadersScreenContent,
            detailsScreenContent = detailsScreenContent
        )
        composable( Screen.Home.route ){
            imagesHeadersScreenContent()
        }

        composable( Screen.Bookmark.route ){
            bookmarkScreenContent()
        }
    }
}