package com.teckzi.rickandmorty.data.repository

import androidx.paging.PagingData
import com.teckzi.rickandmorty.data.local.RickAndMortyDatabase
import com.teckzi.rickandmorty.data.network.RickAndMortyApi
import com.teckzi.rickandmorty.domain.model.CharacterModel
import com.teckzi.rickandmorty.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl(
    private val rickAndMortyApi: RickAndMortyApi,
    private val rickAndMortyDatabase: RickAndMortyDatabase
) : RemoteDataSource {
    private val characterDao = rickAndMortyDatabase.characterDao()

    override fun getAllCharacters(): Flow<PagingData<CharacterModel>> {
        TODO("Not yet implemented")
    }

    override fun searchCharacters(
        name: String?,
        status: String?,
        species: String?,
        type: String?,
        gender: String?
    ): Flow<PagingData<CharacterModel>> {
        TODO("Not yet implemented")
    }
}