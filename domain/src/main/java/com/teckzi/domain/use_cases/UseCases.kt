package com.teckzi.domain.use_cases

import com.teckzi.domain.use_cases.character_use_cases.GetAllCharactersUseCase
import com.teckzi.domain.use_cases.character_use_cases.GetCharacterListById
import com.teckzi.domain.use_cases.character_use_cases.GetSearchedCharacterUseCase
import com.teckzi.domain.use_cases.character_use_cases.GetSelectedCharacterUseCase
import com.teckzi.domain.use_cases.episode_use_cases.GetAllEpisodeUseCase
import com.teckzi.domain.use_cases.episode_use_cases.GetEpisodeListById
import com.teckzi.domain.use_cases.episode_use_cases.GetSearchedEpisodeUseCase
import com.teckzi.domain.use_cases.episode_use_cases.GetSelectedEpisodeUseCase
import com.teckzi.domain.use_cases.location_use_cases.GetAllLocationUseCase
import com.teckzi.domain.use_cases.location_use_cases.GetLocationByName
import com.teckzi.domain.use_cases.location_use_cases.GetSearchedLocationUseCase
import com.teckzi.domain.use_cases.location_use_cases.GetSelectedLocationUseCase

data class UseCases(
    /** Character UseCases */
    val getAllCharactersUseCase: GetAllCharactersUseCase,
    val getSelectedCharacterUseCase: GetSelectedCharacterUseCase,
    val getSearchedCharacterUseCase: GetSearchedCharacterUseCase,
    val getCharacterListById: GetCharacterListById,
    /** Episode UseCases */
    val getAllEpisodeUseCase: GetAllEpisodeUseCase,
    val getSelectedEpisodeUseCase: GetSelectedEpisodeUseCase,
    val getSearchedEpisodeUseCase: GetSearchedEpisodeUseCase,
    val getEpisodeListById: GetEpisodeListById,
    /** Location UseCases */
    val getAllLocationUseCase: GetAllLocationUseCase,
    val getSelectedLocationUseCase: GetSelectedLocationUseCase,
    val getSearchedLocationUseCase: GetSearchedLocationUseCase,
    val getLocationByName: GetLocationByName
)