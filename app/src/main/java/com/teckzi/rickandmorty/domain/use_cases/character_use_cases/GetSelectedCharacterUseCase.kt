package com.teckzi.rickandmorty.domain.use_cases.character_use_cases

import com.teckzi.rickandmorty.domain.model.CharacterModel
import com.teckzi.rickandmorty.domain.repository.IRepository

class GetSelectedCharacterUseCase(
    private val repository: IRepository
) {
    suspend operator fun invoke(characterId: Int): CharacterModel {
        return repository.getSelectedCharacter(characterId)
    }
}