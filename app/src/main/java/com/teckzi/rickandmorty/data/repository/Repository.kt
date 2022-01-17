package com.teckzi.rickandmorty.data.repository

import android.util.Log
import androidx.paging.PagingData
import com.teckzi.rickandmorty.data.mappers.toCharacterModel
import com.teckzi.rickandmorty.domain.model.CharacterModel
import com.teckzi.rickandmorty.domain.repository.LocalDataSource
import com.teckzi.rickandmorty.domain.repository.RemoteDataSource
import com.teckzi.rickandmorty.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val TAG = "TAG Repository"

class Repository @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource
):IRepository {
    override fun getAllCharacters(): Flow<PagingData<CharacterModel>> {
        return remote.getAllCharacters()
    }

    override suspend fun getSelectedCharacter(characterId: Int): CharacterModel {
        return try {
            Log.d(TAG, "get character character remote")
            remote.getCharacterById(id = characterId)
        } catch (e: Exception) {
            Log.d(TAG, "get character character local,e $e")
            local.getSelectedCharacter(id = characterId)
        }
    }

    override suspend fun searchCharacters(
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