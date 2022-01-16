package com.teckzi.rickandmorty.domain.repository

import androidx.paging.PagingData
import com.teckzi.rickandmorty.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllCharacters(): Flow<PagingData<CharacterModel>>

    fun searchCharacters(
        name: String?,
        status: String?,
        species: String?,
        type: String?,
        gender: String?
    ): Flow<PagingData<CharacterModel>>
}