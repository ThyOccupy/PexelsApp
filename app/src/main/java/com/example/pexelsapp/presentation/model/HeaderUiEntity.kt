package com.example.pexelsapp.presentation.model

data class HeaderUiEntity(
    val name: String,
    val isSelected: Boolean
)

fun String.toHeaderUiEntity(): HeaderUiEntity = HeaderUiEntity(
    name = this,
    isSelected = false
)