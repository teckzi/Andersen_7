package com.teckzi.rickandmorty.domain.repository

import androidx.paging.PagingData
import com.teckzi.rickandmorty.domain.model.CharacterModel
import com.teckzi.rickandmorty.domain.model.EpisodeModel
import com.teckzi.rickandmorty.domain.model.LocationModel
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllCharacters(): Flow<PagingData<CharacterModel>>

    suspend fun getCharacterById(id: Int): CharacterModel

    suspend fun searchCharacters(
        name: String?,
        status: String?,
        species: String?,
        type: String?,
        gender: String?
    ): Flow<PagingData<CharacterModel>>

    fun getAllLocations(): Flow<PagingData<LocationModel>>

    suspend fun getLocationById(id: Int): LocationModel

    suspend fun searchLocations(
        name: String?,
        type: String?,
        dimension: String?
    ): Flow<PagingData<LocationModel>>

    fun getAllEpisodes(): Flow<PagingData<EpisodeModel>>

    suspend fun getEpisodeById(id: Int): EpisodeModel

    suspend fun searchEpisodes(
        name: String?,
        episode: String?
    ): Flow<PagingData<EpisodeModel>>
}