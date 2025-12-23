package com.example.pexelsapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PexelsEntity(
    @PrimaryKey val id: Long,
    val urlOrig: String,
    val urlComp: String,
    val width: Int,
    val height: Int,
    val photographer: String,
    val isBookmarked: Boolean
)