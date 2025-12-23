package com.example.pexelsapp.ui

data class HeaderUiEntity(
    val name: String,
    val isSelected: Boolean
)

fun String.toHeaderUiEntity(): HeaderUiEntity = HeaderUiEntity(
    name = this,
    isSelected = false
)