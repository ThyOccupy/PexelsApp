package com.example.pexelsapp.ui.navigation

sealed class Screen (
    val route: String
) {
    object Home: Screen(HOME_ROUTE)
    object Bookmark: Screen(BOOKMARK_ROUTE)

    companion object{
        private const val HOME_ROUTE = "home_screen"
        private const val BOOKMARK_ROUTE = "bookmark_screen"
    }
}