package com.teckzi.rickandmorty.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.teckzi.rickandmorty.data.local.model.CharacterDbo

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character_table ORDER BY id ASC")
    fun getAllCharacters(): List<CharacterDbo>

    @Query("SELECT * FROM character_table WHERE id=:characterId")
    fun getSelectedCharacter(characterId: Int): CharacterDbo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacters(characters: List<CharacterDbo>)

    @Query("SELECT * FROM character_table WHERE name LIKE IFNULL(:name, '%%') AND status LIKE IFNULL(:status, '%%') AND species LIKE IFNULL(:species, '%%') AND type LIKE IFNULL(:type, '%%') AND gender LIKE IFNULL(:gender, '%%') ORDER BY id ASC")
    suspend fun getFilteredCharacters(
        name: String?,
        status: String?,
        species: String?,
        type: String?,
        gender: String?
    ): List<CharacterDbo>
}