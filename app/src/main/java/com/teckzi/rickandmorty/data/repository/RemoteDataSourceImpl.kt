package com.teckzi.rickandmorty.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.teckzi.rickandmorty.data.local.RickAndMortyDatabase
import com.teckzi.rickandmorty.data.mappers.toCharacterModel
import com.teckzi.rickandmorty.data.mappers.toEpisodeModel
import com.teckzi.rickandmorty.data.mappers.toLocationModel
import com.teckzi.rickandmorty.data.network.RickAndMortyApi
import com.teckzi.rickandmorty.data.paging_source.character_paging.CharacterPagingSource
import com.teckzi.rickandmorty.data.paging_source.character_paging.SearchCharacterSource
import com.teckzi.rickandmorty.data.paging_source.episode_paging.EpisodePagingSource
import com.teckzi.rickandmorty.data.paging_source.episode_paging.SearchEpisodeSource
import com.teckzi.rickandmorty.data.paging_source.location_paging.LocationPagingSource
import com.teckzi.rickandmorty.data.paging_source.location_paging.SearchLocationSource
import com.teckzi.rickandmorty.domain.model.CharacterModel
import com.teckzi.rickandmorty.domain.model.EpisodeModel
import com.teckzi.rickandmorty.domain.model.LocationModel
import com.teckzi.rickandmorty.domain.repository.RemoteDataSource
import com.teckzi.rickandmorty.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl(
    private val rickAndMortyApi: RickAndMortyApi,
    private val rickAndMortyDatabase: RickAndMortyDatabase
) : RemoteDataSource {


    override fun getAllCharacters() = Pager(
        config = PagingConfig(pageSize = ITEMS_PER_PAGE),
        pagingSourceFactory = {
            CharacterPagingSource(
                rickAndMortyApi = rickAndMortyApi,
                rickAndMortyDatabase = rickAndMortyDatabase
            )
        }
    ).flow

    override fun getAllLocations() = Pager(
        config = PagingConfig(pageSize = ITEMS_PER_PAGE),
        pagingSourceFactory = {
            LocationPagingSource(
                rickAndMortyApi = rickAndMortyApi,
                rickAndMortyDatabase = rickAndMortyDatabase
            )
        }
    ).flow

    override fun getAllEpisodes() = Pager(
        config = PagingConfig(pageSize = ITEMS_PER_PAGE),
        pagingSourceFactory = {
            EpisodePagingSource(
                rickAndMortyApi = rickAndMortyApi,
                rickAndMortyDatabase = rickAndMortyDatabase
            )
        }
    ).flow

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

    override suspend fun searchLocations(
        name: String?,
        type: String?,
        dimension: String?
    ): Flow<PagingData<LocationModel>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchLocationSource(
                    rickAndMortyApi = rickAndMortyApi,
                    rickAndMortyDatabase = rickAndMortyDatabase,
                    name = name,
                    type = type,
                    dimension = dimension
                )
            }
        ).flow
    }

    override suspend fun searchEpisodes(
        name: String?,
        episode: String?
    ): Flow<PagingData<EpisodeModel>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchEpisodeSource(
                    rickAndMortyApi = rickAndMortyApi,
                    rickAndMortyDatabase = rickAndMortyDatabase,
                    name = name,
                    episode = episode
                )
            }
        ).flow
    }
}