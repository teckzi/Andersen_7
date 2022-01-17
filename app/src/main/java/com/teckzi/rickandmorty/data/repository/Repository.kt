package com.teckzi.rickandmorty.data.repository

import androidx.paging.PagingData
import com.teckzi.rickandmorty.domain.model.CharacterModel
import com.teckzi.rickandmorty.domain.model.EpisodeModel
import com.teckzi.rickandmorty.domain.model.LocationModel
import com.teckzi.rickandmorty.domain.repository.IRepository
import com.teckzi.rickandmorty.domain.repository.LocalDataSource
import com.teckzi.rickandmorty.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource
) : IRepository {
    override fun getAllCharacters(): Flow<PagingData<CharacterModel>> {
        return remote.getAllCharacters()
    }

    override fun getAllLocation(): Flow<PagingData<LocationModel>> {
        return remote.getAllLocations()
    }

    override fun getAllEpisodes(): Flow<PagingData<EpisodeModel>> {
        return remote.getAllEpisodes()
    }

    override suspend fun getSelectedCharacter(characterId: Int): CharacterModel {
        return local.getSelectedCharacter(id = characterId)
    }

    override suspend fun getSelectedLocation(locationId: Int): LocationModel {
        return local.getSelectedLocation(id = locationId)
    }

    override suspend fun getSelectedEpisode(episodeId: Int): EpisodeModel {
        return local.getSelectedEpisode(id = episodeId)
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

    override suspend fun searchLocation(
        name: String?,
        type: String?,
        dimension: String?
    ): Flow<PagingData<LocationModel>> {
        return remote.searchLocations(
            name = name,
            type = type,
            dimension = dimension
        )
    }

    override suspend fun searchEpisode(
        name: String?,
        episode: String?
    ): Flow<PagingData<EpisodeModel>> {
        return remote.searchEpisodes(
            name = name,
            episode = episode
        )
    }
}