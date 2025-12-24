package com.example.pexelsapp.presentation.screen.root

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.pexelsapp.R
import com.example.pexelsapp.presentation.common.navigation.Screen

sealed class NavigationItem(
    val screen: Screen,
    val iconActive: @Composable () -> Painter,
    val iconInactive: @Composable () -> Painter,
){
    object Home: NavigationItem(
        screen = Screen.Home,
        iconActive = { painterResource(R.drawable.ic_navigation_home_active) },
        iconInactive = { painterResource(R.drawable.ic_navigation_home_inactive) }
    )
    object Bookmarked: NavigationItem(
        screen = Screen.Bookmark,
        iconActive = { painterResource(R.drawable.ic_bookmark_active) },
        iconInactive = { painterResource(R.drawable.ic_bookmark_inactive) }
    )
}