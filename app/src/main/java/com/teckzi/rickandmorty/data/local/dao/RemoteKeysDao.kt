package com.teckzi.rickandmorty.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.teckzi.rickandmorty.data.local.model.remote_keys.CharacterRemoteKeysDbo

@Dao
interface RemoteKeysDao {

    /**Character keys */
    @Query("SELECT * FROM character_remote_key_table WHERE id=:id")
    suspend fun getCharacterRemoteKey(id: Int): CharacterRemoteKeysDbo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllCharacterRemoteKeys(characterRemoteKeys: List<CharacterRemoteKeysDbo>)

    @Query("DELETE FROM character_remote_key_table")
    suspend fun deleteAllCharacterRemoteKeys()
}