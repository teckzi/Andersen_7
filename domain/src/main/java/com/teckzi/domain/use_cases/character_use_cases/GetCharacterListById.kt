package com.teckzi.domain.use_cases.character_use_cases

import com.teckzi.domain.model.CharacterModel
import com.teckzi.domain.repository.IRepository

class GetCharacterListById(
    private val repository: IRepository
) {
    suspend operator fun invoke(characterIdList: List<Int>): List<CharacterModel> {
        return repository.getCharacterListById(characterIdList)
    }
}