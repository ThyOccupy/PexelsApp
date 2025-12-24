package com.example.pexelsapp.presentation.events

sealed class HomeScreenEvent {

    data class OnSearchQueryChange(val query: String) : HomeScreenEvent()
    object OnExploreClicked : HomeScreenEvent()
    object onRetryClicked: HomeScreenEvent()

}