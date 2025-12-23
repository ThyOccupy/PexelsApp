package com.example.pexelsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class NavigationState(
    //This object stores navigation state
    val navHostController: NavHostController
) {

    fun navigateTo(route: String) {
        navHostController.navigate(route) {
            //remove storing the NavigationBar history in the backstack, except for the HomeScreen
            popUpTo(navHostController.graph.startDestinationId) {
                //when removing screens from the backStack, save their state
                saveState = true
            }
            //prevent multiple saving of the same function to the backstack
            //or Only one copy of each screen is on top of the backStack
            launchSingleTop = true
            //during navigation, when switching to a screen, restore the state
            restoreState = true
        }

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