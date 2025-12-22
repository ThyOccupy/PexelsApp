package com.example.pexelsapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pexelsapp.data.database.entities.PexelsEntity

@Database(
    entities = [PexelsEntity::class],
    version = 1
)
abstract class PexelsDatabase : RoomDatabase() {
    abstract val pexelsDao: PexelsDao
}
