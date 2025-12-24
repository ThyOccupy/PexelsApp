package com.example.pexelsapp.presentation.events

sealed class DetailsScreenEvent {
    data class onInitEvent(val id: Int) : DetailsScreenEvent()
}