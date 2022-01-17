package com.teckzi.rickandmorty.domain.use_cases

import com.teckzi.rickandmorty.domain.use_cases.character_use_cases.GetAllCharactersUseCase
import com.teckzi.rickandmorty.domain.use_cases.character_use_cases.GetSearchedCharacterUseCase
import com.teckzi.rickandmorty.domain.use_cases.character_use_cases.GetSelectedCharacterUseCase
import com.teckzi.rickandmorty.domain.use_cases.episode_use_cases.GetAllEpisodeUseCase
import com.teckzi.rickandmorty.domain.use_cases.episode_use_cases.GetSearchedEpisodeUseCase
import com.teckzi.rickandmorty.domain.use_cases.episode_use_cases.GetSelectedEpisodeUseCase
import com.teckzi.rickandmorty.domain.use_cases.location_use_cases.GetAllLocationUseCase
import com.teckzi.rickandmorty.domain.use_cases.location_use_cases.GetSearchedLocationUseCase
import com.teckzi.rickandmorty.domain.use_cases.location_use_cases.GetSelectedLocationUseCase

data class UseCases(
    val getAllCharactersUseCase: GetAllCharactersUseCase,
    val getSelectedCharacterUseCase: GetSelectedCharacterUseCase,
    val getSearchedCharacterUseCase: GetSearchedCharacterUseCase,
    val getAllEpisodeUseCase: GetAllEpisodeUseCase,
    val getSelectedEpisodeUseCase: GetSelectedEpisodeUseCase,
    val getSearchedEpisodeUseCase: GetSearchedEpisodeUseCase,
    val getAllLocationUseCase: GetAllLocationUseCase,
    val getSelectedLocationUseCase: GetSelectedLocationUseCase,
    val getSearchedLocationUseCase: GetSearchedLocationUseCase
)