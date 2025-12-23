package com.example.pexelsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(
    navHostController: NavHostController,
    homeScreenContent: @Composable () -> Unit,
    bookmarkScreenContent: @Composable () -> Unit,
    modifier: Modifier = Modifier
    ) {

    //Store app navGraph
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ){
        //Add new destination in navGraph
        composable( Screen.Home.route ){
            //Use composable callback to avoid injecting viewModel and paddingValues and so on
            homeScreenContent()
        }

        composable( Screen.Bookmark.route ){
            //Use composable callback to avoid injecting viewModel and paddingValues and so on
            bookmarkScreenContent()
        }
    }
}