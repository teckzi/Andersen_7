package com.teckzi.rickandmorty.data.repository

import com.teckzi.rickandmorty.data.local.RickAndMortyDatabase
import com.teckzi.rickandmorty.data.mappers.toCharacterModel
import com.teckzi.rickandmorty.domain.model.CharacterModel
import com.teckzi.rickandmorty.domain.repository.LocalDataSource

class LocalDataSourceImpl(rickAndMortyDatabase: RickAndMortyDatabase) : LocalDataSource {

    private val characterDao = rickAndMortyDatabase.characterDao()

    override suspend fun getSelectedCharacter(id: Int): CharacterModel {
        return characterDao.getSelectedCharacter(characterId = id).toCharacterModel()
    }

}