package com.teckzi.rickandmorty.domain.use_cases.character_use_cases

import androidx.paging.PagingData
import com.teckzi.rickandmorty.data.repository.Repository
import com.teckzi.rickandmorty.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

class GetAllCharactersUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<CharacterModel>> {
        return repository.getAllCharacters()
    }
}