package com.example.pexelsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType.Companion.IntType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation

fun NavGraphBuilder.homeScreenNavGraph(
    imagesHeadersScreenContent: @Composable () -> Unit,
    detailsScreenContent: @Composable (photoId: Int) -> Unit,
) {

    navigation(
        startDestination = Screen.Home.route,
        route = Screen.NestedHome.route
    ){
        composable( Screen.Home.route ){
            imagesHeadersScreenContent()
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument(name = Screen.KEY_PHOTO_ID){
                   type = IntType
                }
            )
        ){
            val photoId = it.arguments?.getInt(Screen.KEY_PHOTO_ID) ?: 0
            detailsScreenContent(photoId)
        }
    }

}