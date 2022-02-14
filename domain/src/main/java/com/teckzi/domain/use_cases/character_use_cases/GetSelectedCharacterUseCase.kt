package com.teckzi.domain.use_cases.character_use_cases

import com.teckzi.domain.model.CharacterModel
import com.teckzi.domain.repository.IRepository

class GetSelectedCharacterUseCase(
    private val repository: IRepository
) {
    suspend operator fun invoke(characterId: Int): CharacterModel {
        return repository.getCharacterById(characterId)
    }
}