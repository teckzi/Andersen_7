package com.teckzi.rickandmorty.domain.use_cases

import com.teckzi.rickandmorty.domain.use_cases.character_use_cases.GetAllCharactersUseCase
import com.teckzi.rickandmorty.domain.use_cases.character_use_cases.GetSearchedCharacterUseCase
import com.teckzi.rickandmorty.domain.use_cases.character_use_cases.GetSelectedCharacterUseCase

data class UseCases(
    val getAllCharactersUseCase: GetAllCharactersUseCase,
    val getSelectedCharacterUseCase: GetSelectedCharacterUseCase,
    val getSearchedCharacterUseCase: GetSearchedCharacterUseCase
)