package com.teckzi.rickandmorty.data.repository

import androidx.paging.PagingData
import com.teckzi.rickandmorty.domain.model.CharacterModel
import com.teckzi.rickandmorty.domain.repository.LocalDataSource
import com.teckzi.rickandmorty.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource
) {
    fun getAllCharacters(): Flow<PagingData<CharacterModel>> {
        return remote.getAllCharacters()
    }
    suspend fun getSelectedCharacter(characterId: Int): CharacterModel {
        return local.getSelectedCharacter(id = characterId)
    }

    fun searchCharacters(
        name: String?,
        status: String?,
        species: String?,
        type: String?,
        gender: String?
    ): Flow<PagingData<CharacterModel>> {
        return remote.searchCharacters(
            name = name,
            status = status,
            species = species,
            type = type,
            gender = gender
        )
    }
}