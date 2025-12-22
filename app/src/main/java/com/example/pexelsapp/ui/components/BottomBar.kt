package com.example.pexelsapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.pexelsapp.ui.components.NavigationItem.*
import com.example.pexelsapp.ui.navigation.NavigationState
import com.example.pexelsapp.ui.navigation.Screen

@Composable
fun BottomBar(
    navigationState: NavigationState
) {
    /*This state stores current open screen.
        If the screen changes, its state will also change and a recomposition comp funs will occur.*/

    val navBackStackEntry = navigationState.navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route ?: Screen.Home.route

    val items = listOf(Home, Bookmarked)

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                onClick = { navigationState.navigateTo(item.screen.route) },
                icon = {
                    Icon(
                        painter = item.iconInactive(),
                        contentDescription = null
                    )
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    selectedIndicatorColor = Color.Unspecified,
                    unselectedIconColor = MaterialTheme.colorScheme.secondary,
                    unselectedTextColor = MaterialTheme.colorScheme.secondary,
                    disabledIconColor = MaterialTheme.colorScheme.secondary,
                    disabledTextColor = MaterialTheme.colorScheme.secondary
                )
            )
        }
    }
}