package com.teckzi.rickandmorty.domain.repository

import com.teckzi.rickandmorty.domain.model.CharacterModel

interface LocalDataSource {
    suspend fun getSelectedCharacter(id: Int): CharacterModel
}