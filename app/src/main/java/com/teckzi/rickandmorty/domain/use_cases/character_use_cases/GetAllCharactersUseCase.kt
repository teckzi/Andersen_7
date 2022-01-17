package com.teckzi.rickandmorty.domain.use_cases.character_use_cases

import androidx.paging.PagingData
import com.teckzi.rickandmorty.domain.model.CharacterModel
import com.teckzi.rickandmorty.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow

class GetAllCharactersUseCase(
    private val repository: IRepository
) {
    operator fun invoke(): Flow<PagingData<CharacterModel>> {
        return repository.getAllCharacters()
    }
}