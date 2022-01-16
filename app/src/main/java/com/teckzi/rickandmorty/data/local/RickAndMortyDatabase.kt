package com.teckzi.rickandmorty.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.teckzi.rickandmorty.data.local.dao.CharacterDao
import com.teckzi.rickandmorty.data.local.dao.RemoteKeysDao
import com.teckzi.rickandmorty.data.local.model.CharacterDbo
import com.teckzi.rickandmorty.data.local.model.remote_keys.CharacterRemoteKeysDbo

@Database(entities = [CharacterDbo::class, CharacterRemoteKeysDbo::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class RickAndMortyDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}