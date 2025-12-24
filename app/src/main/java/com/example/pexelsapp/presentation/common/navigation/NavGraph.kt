package com.example.pexelsapp.presentation.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(
    navHostController: NavHostController,
    imagesHeadersScreenContent: @Composable () -> Unit,
    bookmarkScreenContent: @Composable () -> Unit,
    detailsScreenContent: @Composable (photoId: Int, route: String) -> Unit,
    modifier: Modifier = Modifier
    ) {

    //Store app navGraph
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ){
        homeScreenNavGraph(
            imagesHeadersScreenContent = imagesHeadersScreenContent,
            detailsScreenContent = detailsScreenContent
        )
        composableWithAnimation( Screen.NestedHome.route ){
            imagesHeadersScreenContent()
        }

        composableWithAnimation( Screen.Bookmark.route ){
            bookmarkScreenContent()
        }
    }
}