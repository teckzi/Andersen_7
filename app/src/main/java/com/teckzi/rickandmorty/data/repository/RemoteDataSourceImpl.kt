package com.teckzi.rickandmorty.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.teckzi.rickandmorty.data.local.RickAndMortyDatabase
import com.teckzi.rickandmorty.data.mappers.toCharacterModel
import com.teckzi.rickandmorty.data.network.RickAndMortyApi
import com.teckzi.rickandmorty.data.network.model.CharacterDto
import com.teckzi.rickandmorty.data.paging_source.character_paging.CharacterPagingSource
import com.teckzi.rickandmorty.data.paging_source.character_paging.SearchCharacterSource
import com.teckzi.rickandmorty.domain.model.CharacterModel
import com.teckzi.rickandmorty.domain.repository.RemoteDataSource
import com.teckzi.rickandmorty.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl(
    private val rickAndMortyApi: RickAndMortyApi,
    private val rickAndMortyDatabase: RickAndMortyDatabase
) : RemoteDataSource {


    override fun getAllCharacters() = Pager(
        config = PagingConfig(enablePlaceholders = true, pageSize = ITEMS_PER_PAGE),
        pagingSourceFactory = {
            CharacterPagingSource(
                rickAndMortyApi = rickAndMortyApi,
                rickAndMortyDatabase = rickAndMortyDatabase
            )
        }
    ).flow

    override suspend fun getCharacterById(id: Int): CharacterDto {
        val response = rickAndMortyApi.getCharacterById(id)
        return response.results[0]
    }

    override suspend fun searchCharacters(
        name: String?,
        status: String?,
        species: String?,
        type: String?,
        gender: String?
    ): Flow<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchCharacterSource(
                    rickAndMortyApi = rickAndMortyApi,
                    rickAndMortyDatabase = rickAndMortyDatabase,
                    name = name,
                    status = status,
                    species = species,
                    type = type,
                    gender = gender
                )
            }
        ).flow
    }
}