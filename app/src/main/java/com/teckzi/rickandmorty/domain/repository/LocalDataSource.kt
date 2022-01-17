package com.teckzi.rickandmorty.domain.repository

import com.teckzi.rickandmorty.domain.model.CharacterModel
import com.teckzi.rickandmorty.domain.model.EpisodeModel
import com.teckzi.rickandmorty.domain.model.LocationModel

interface LocalDataSource {
    suspend fun getSelectedCharacter(id: Int): CharacterModel

    suspend fun getSelectedLocation(id: Int): LocationModel

    suspend fun getSelectedEpisode(id: Int): EpisodeModel
}