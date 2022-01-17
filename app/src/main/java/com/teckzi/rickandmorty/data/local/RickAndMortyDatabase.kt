package com.teckzi.rickandmorty.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.teckzi.rickandmorty.data.local.dao.CharacterDao
import com.teckzi.rickandmorty.data.local.dao.EpisodeDao
import com.teckzi.rickandmorty.data.local.dao.LocationDao
import com.teckzi.rickandmorty.data.local.model.CharacterDbo
import com.teckzi.ricks.data.local.model.EpisodeDbo
import com.teckzi.ricks.data.local.model.LocationDbo

@Database(entities = [CharacterDbo::class,LocationDbo::class,EpisodeDbo::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class RickAndMortyDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun locationDao(): LocationDao
    abstract fun episodeDao(): EpisodeDao
}