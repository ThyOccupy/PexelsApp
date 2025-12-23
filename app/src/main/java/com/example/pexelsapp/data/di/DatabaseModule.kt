package com.example.pexelsapp.data.di

import android.content.Context
import androidx.room.Room
import com.example.pexelsapp.data.database.PexelsDao
import com.example.pexelsapp.data.database.PexelsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : PexelsDatabase {
        return Room.databaseBuilder( context, PexelsDatabase::class.java, "pexels.db")
            .build()
    }

    @Provides
    @Singleton
    fun providePexelsDao(database: PexelsDatabase): PexelsDao = database.pexelsDao


}