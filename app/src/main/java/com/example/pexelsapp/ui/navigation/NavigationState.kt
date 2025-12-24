package com.example.pexelsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pexelsapp.ui.PhotoUiEntity

class NavigationState(
    //This object stores navigation state
    val navHostController: NavHostController
) {

    fun navigateTo(route: String) {
        navHostController.navigate(route) {
            popUpTo(navHostController.graph.findStartDestination().id)
            launchSingleTop = true
        }
    }
    fun navigateToDetails(photo: PhotoUiEntity){
        navHostController.navigate(Screen.Details.getRouteWithArgs(photo))
    }
}

//Hide the implementation inside the NavigationState class
@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): NavigationState {
    //To prevent an instance of this class from being recreated during recomposition,
    // we create an object of this class using the remember function.
    return remember {
        NavigationState(navHostController)
    }
}