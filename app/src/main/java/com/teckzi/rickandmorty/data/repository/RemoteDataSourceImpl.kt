package com.teckzi.rickandmorty.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.teckzi.rickandmorty.data.local.RickAndMortyDatabase
import com.teckzi.rickandmorty.data.mappers.toCharacterModel
import com.teckzi.rickandmorty.data.network.RickAndMortyApi
import com.teckzi.rickandmorty.data.paging_source.CharacterPagingSource
import com.teckzi.rickandmorty.domain.model.CharacterModel
import com.teckzi.rickandmorty.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl(
    private val rickAndMortyApi: RickAndMortyApi,
    private val rickAndMortyDatabase: RickAndMortyDatabase
) : RemoteDataSource {


    override fun getAllCharacters() = Pager(
        config = PagingConfig(enablePlaceholders = false, pageSize = 20),
        pagingSourceFactory = {
            CharacterPagingSource(
                rickAndMortyApi = rickAndMortyApi,
                rickAndMortyDatabase = rickAndMortyDatabase
            )
        }
    ).flow

    override suspend fun getCharacterById(id: Int): CharacterModel {
        val response = rickAndMortyApi.getCharacterById(id)
        return response.results[0].toCharacterModel()
    }

    override suspend fun searchCharacters(
        name: String?,
        status: String?,
        species: String?,
        type: String?,
        gender: String?
    ): Flow<PagingData<CharacterModel>> {
        TODO("Not yet implemented")
    }
}