package com.example.pexelsapp.presentation.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.rememberNavController
import com.example.pexelsapp.presentation.model.PhotoUiEntity

class NavigationState(
    //This object stores navigation state
    val navHostController: NavHostController
) {
    fun navigateTo(route: String) {
        navHostController.navigate(route) {
            getInnerNavigationSettings()
        }
    }
    fun navigateToDetails(photo: PhotoUiEntity, route: String) {
        navHostController.navigate(Screen.Details.getRouteWithArgs(photo, route))
    }
    fun navigateToHome(){
        navHostController.navigate(Screen.Home.route){
            getInnerNavigationSettings()
        }
    }
    fun goBack() {
        navHostController.popBackStack()
    }

    private fun NavOptionsBuilder.getInnerNavigationSettings(){
        popUpTo(navHostController.graph.findStartDestination().id)
        launchSingleTop = true
    }
}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): NavigationState {
    return remember {
        NavigationState(navHostController)
    }
}