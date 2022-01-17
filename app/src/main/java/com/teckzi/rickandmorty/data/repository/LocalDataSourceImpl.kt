package com.teckzi.rickandmorty.data.repository

import com.teckzi.rickandmorty.data.local.RickAndMortyDatabase
import com.teckzi.rickandmorty.data.mappers.toCharacterModel
import com.teckzi.rickandmorty.data.mappers.toEpisodeModel
import com.teckzi.rickandmorty.data.mappers.toLocationModel
import com.teckzi.rickandmorty.domain.model.CharacterModel
import com.teckzi.rickandmorty.domain.model.EpisodeModel
import com.teckzi.rickandmorty.domain.model.LocationModel
import com.teckzi.rickandmorty.domain.repository.LocalDataSource

class LocalDataSourceImpl(rickAndMortyDatabase: RickAndMortyDatabase) : LocalDataSource {

    private val characterDao = rickAndMortyDatabase.characterDao()
    private val locationDao = rickAndMortyDatabase.locationDao()
    private val episodeDao = rickAndMortyDatabase.episodeDao()

    override suspend fun getSelectedCharacter(id: Int): CharacterModel {
        return characterDao.getSelectedCharacter(characterId = id).toCharacterModel()
    }

    override suspend fun getSelectedLocation(id: Int): LocationModel {
        return locationDao.getSelectedLocation(locationId = id).toLocationModel()
    }

    override suspend fun getSelectedEpisode(id: Int): EpisodeModel {
        return episodeDao.getSelectedEpisode(episodeId = id).toEpisodeModel()
    }

}