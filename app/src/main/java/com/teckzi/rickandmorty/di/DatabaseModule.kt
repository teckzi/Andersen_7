package com.teckzi.rickandmorty.di

import android.content.Context
import androidx.room.Room
import com.teckzi.rickandmorty.data.local.RickAndMortyDatabase
import com.teckzi.rickandmorty.data.repository.LocalDataSourceImpl
import com.teckzi.rickandmorty.domain.repository.LocalDataSource
import com.teckzi.rickandmorty.util.Constants.RICK_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): RickAndMortyDatabase {
        return Room.databaseBuilder(
            context,
            RickAndMortyDatabase::class.java,
            RICK_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(
        rickAndMortyDatabase: RickAndMortyDatabase
    ): LocalDataSource {
        return LocalDataSourceImpl(
            rickAndMortyDatabase = rickAndMortyDatabase
        )
    }
}