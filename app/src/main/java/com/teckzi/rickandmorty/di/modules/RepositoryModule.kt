package com.teckzi.rickandmorty.di.modules

import com.teckzi.rickandmorty.data.repository.Repository
import com.teckzi.rickandmorty.domain.use_cases.UseCases
import com.teckzi.rickandmorty.domain.use_cases.character_use_cases.GetAllCharactersUseCase
import com.teckzi.rickandmorty.domain.use_cases.character_use_cases.GetCharacterListById
import com.teckzi.rickandmorty.domain.use_cases.character_use_cases.GetSearchedCharacterUseCase
import com.teckzi.rickandmorty.domain.use_cases.character_use_cases.GetSelectedCharacterUseCase
import com.teckzi.rickandmorty.domain.use_cases.episode_use_cases.GetAllEpisodeUseCase
import com.teckzi.rickandmorty.domain.use_cases.episode_use_cases.GetEpisodeListById
import com.teckzi.rickandmorty.domain.use_cases.episode_use_cases.GetSearchedEpisodeUseCase
import com.teckzi.rickandmorty.domain.use_cases.episode_use_cases.GetSelectedEpisodeUseCase
import com.teckzi.rickandmorty.domain.use_cases.location_use_cases.GetAllLocationUseCase
import com.teckzi.rickandmorty.domain.use_cases.location_use_cases.GetLocationByName
import com.teckzi.rickandmorty.domain.use_cases.location_use_cases.GetSearchedLocationUseCase
import com.teckzi.rickandmorty.domain.use_cases.location_use_cases.GetSelectedLocationUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            /** Character UseCases */
            getAllCharactersUseCase = GetAllCharactersUseCase(repository),
            getSelectedCharacterUseCase = GetSelectedCharacterUseCase(repository),
            getSearchedCharacterUseCase = GetSearchedCharacterUseCase(repository),
            getCharacterListById = GetCharacterListById(repository),
            /** Episode UseCases */
            getAllEpisodeUseCase = GetAllEpisodeUseCase(repository),
            getSelectedEpisodeUseCase = GetSelectedEpisodeUseCase(repository),
            getSearchedEpisodeUseCase = GetSearchedEpisodeUseCase(repository),
            getEpisodeListById = GetEpisodeListById(repository),
            /** Location UseCases */
            getAllLocationUseCase = GetAllLocationUseCase(repository),
            getSelectedLocationUseCase = GetSelectedLocationUseCase(repository),
            getSearchedLocationUseCase = GetSearchedLocationUseCase(repository),
            getLocationByName = GetLocationByName(repository)
        )
    }
}