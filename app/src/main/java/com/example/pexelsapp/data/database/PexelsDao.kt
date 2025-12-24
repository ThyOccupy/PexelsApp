package com.example.pexelsapp.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.pexelsapp.data.database.entities.PexelsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PexelsDao {
    @Upsert
    suspend fun upsertAll(recipes: List<PexelsEntity>)

    @Query("SELECT * FROM PexelsEntity")
    fun pagingSource(): PagingSource<Int, PexelsEntity>

    @Query("DELETE FROM PexelsEntity WHERE isBookmarked = 0")
    suspend fun clearAll()

    @Query("SELECT * FROM PexelsEntity WHERE id = :id")
    fun getFromDbById(id: Int): Flow<PexelsEntity>

    @Query("SELECT * FROM PexelsEntity WHERE isBookmarked = 1")
    fun getBookmarked(): PagingSource<Int, PexelsEntity>

    @Query("UPDATE PexelsEntity SET isBookmarked = :isBookmarked WHERE id = :id")
    suspend fun switchBookmarkStatus(id: Int, isBookmarked: Boolean)
}