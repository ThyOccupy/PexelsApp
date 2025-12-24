package com.example.pexelsapp.presentation.navigation

import com.example.pexelsapp.presentation.model.PhotoUiEntity

sealed class Screen (
    val route: String
) {
    object Home: Screen(HOME_ROUTE)
    object Bookmark: Screen(BOOKMARK_ROUTE)

    object NestedHome: Screen(NESTED_HOME_ROUTE)
    object Details: Screen(DETAILS_ROUTE){
        private const val ROUTE_FOR_ARGS = "details_screen"
        fun getRouteWithArgs(photo: PhotoUiEntity, route: String): String = "$ROUTE_FOR_ARGS/${photo.id}/$route"
    }

    companion object{
        const val KEY_ROUTE = "route"
        const val KEY_PHOTO_ID = "photo_id"
        private const val HOME_ROUTE = "home_screen"
        private const val BOOKMARK_ROUTE = "bookmark_screen"
        private const val NESTED_HOME_ROUTE = "nested_home"
        private const val DETAILS_ROUTE = "details_screen/{$KEY_PHOTO_ID}/{$KEY_ROUTE}"
    }
}