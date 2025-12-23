package com.example.pexelsapp.ui.events

sealed class HomeScreenEvent {

    data class OnSearchQueryChange(val query: String) : HomeScreenEvent()
    object OnExploreClicked : HomeScreenEvent()

}