package com.example.pexelsapp.presentation.screen.root

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import com.example.pexelsapp.presentation.common.drawable.PexelsIcons
import com.example.pexelsapp.presentation.navigation.Screen

sealed class NavigationItem(
    val screen: Screen,
    val iconActive: @Composable () -> Painter,
    val iconInactive: @Composable () -> Painter,
){
    object Home: NavigationItem(
        screen = Screen.Home,
        iconActive = { PexelsIcons.HomeFilled },
        iconInactive = { PexelsIcons.HomeOutline }
    )
    object Bookmarked: NavigationItem(
        screen = Screen.Bookmark,
        iconActive = { PexelsIcons.BookmarkFilled },
        iconInactive = { PexelsIcons.BookmarkOutline }
    )
}