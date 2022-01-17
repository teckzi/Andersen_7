package com.teckzi.rickandmorty.domain.repository

import androidx.paging.PagingData
import com.teckzi.rickandmorty.domain.model.CharacterModel
import com.teckzi.rickandmorty.domain.model.EpisodeModel
import com.teckzi.rickandmorty.domain.model.LocationModel
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

    fun getAllLocation(): Flow<PagingData<LocationModel>>
    suspend fun getSelectedLocation(locationId: Int): LocationModel
    suspend fun searchLocation(
        name: String?,
        type: String?,
        dimension: String?
    ): Flow<PagingData<LocationModel>>

    fun getAllEpisodes(): Flow<PagingData<EpisodeModel>>
    suspend fun getSelectedEpisode(episodeId: Int): EpisodeModel
    suspend fun searchEpisode(
        name: String?,
        episode: String?
    ): Flow<PagingData<EpisodeModel>>
}