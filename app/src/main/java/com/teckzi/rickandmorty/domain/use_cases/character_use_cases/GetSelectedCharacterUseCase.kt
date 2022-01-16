package com.teckzi.rickandmorty.domain.use_cases.character_use_cases

import com.teckzi.rickandmorty.data.repository.Repository
import com.teckzi.rickandmorty.domain.model.CharacterModel

class GetSelectedCharacterUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(characterId: Int): CharacterModel {
        return repository.getSelectedCharacter(characterId)
    }
}