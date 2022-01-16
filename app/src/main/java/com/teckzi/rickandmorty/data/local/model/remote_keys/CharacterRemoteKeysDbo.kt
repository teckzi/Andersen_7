package com.teckzi.rickandmorty.data.local.model.remote_keys

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.teckzi.rickandmorty.util.Constants.CHARACTER_REMOTE_KEY_TABLE

@Entity(tableName = CHARACTER_REMOTE_KEY_TABLE)
data class CharacterRemoteKeysDbo(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val nextPage: Int?,
    val prevPage: Int?
)