package com.example.pexelsapp.presentation.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType.Companion.IntType
import androidx.navigation.NavType.Companion.StringType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation

fun NavGraphBuilder.homeScreenNavGraph(
    imagesHeadersScreenContent: @Composable () -> Unit,
    detailsScreenContent: @Composable (photoId: Int, route: String) -> Unit,
) {

    navigation(
        startDestination = Screen.NestedHome.route,
        route = Screen.Home.route
    ){
        composable( Screen.NestedHome.route ){
            imagesHeadersScreenContent()
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument(name = Screen.KEY_PHOTO_ID){
                   type = IntType}
                ,
                navArgument(name = Screen.KEY_ROUTE){
                    type = StringType
                }
            )
        ){
            val photoId = it.arguments?.getInt(Screen.KEY_PHOTO_ID) ?: throw IllegalStateException()
            val route = it.arguments?.getString(Screen.KEY_ROUTE) ?: throw IllegalStateException()
            detailsScreenContent(photoId, route)
        }
    }

}