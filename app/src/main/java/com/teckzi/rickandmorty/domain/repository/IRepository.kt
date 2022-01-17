package com.teckzi.rickandmorty.domain.repository

import androidx.paging.PagingData
import com.teckzi.rickandmorty.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface IRepository {

    fun getAllCharacters(): Flow<PagingData<CharacterModel>>

    suspend fun getSelectedCharacter(characterId: Int): CharacterModel

    suspend fun searchCharacters(
        name: String?,
        status: String?,
        species: String?,
        type: String?,
        gender: String?
    ): Flow<PagingData<CharacterModel>>
}