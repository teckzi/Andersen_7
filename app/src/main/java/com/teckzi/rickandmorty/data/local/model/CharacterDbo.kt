package com.teckzi.rickandmorty.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.teckzi.rickandmorty.util.Constants.CHARACTER_DATABASE_TABLE

@Entity(tableName = CHARACTER_DATABASE_TABLE)
data class CharacterDbo(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var name: String,
    var status: String,
    var species: String,
    var type: String,
    var gender: String,
    var origin: Int,
    var location: Int,
    var image: String,
    var episode: List<Int>
)