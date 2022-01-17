package com.teckzi.rickandmorty.di

import com.teckzi.rickandmorty.data.repository.Repository
import com.teckzi.rickandmorty.domain.use_cases.UseCases
import com.teckzi.rickandmorty.domain.use_cases.character_use_cases.GetAllCharactersUseCase
import com.teckzi.rickandmorty.domain.use_cases.character_use_cases.GetSearchedCharacterUseCase
import com.teckzi.rickandmorty.domain.use_cases.character_use_cases.GetSelectedCharacterUseCase
import com.teckzi.rickandmorty.domain.use_cases.episode_use_cases.GetAllEpisodeUseCase
import com.teckzi.rickandmorty.domain.use_cases.episode_use_cases.GetSearchedEpisodeUseCase
import com.teckzi.rickandmorty.domain.use_cases.episode_use_cases.GetSelectedEpisodeUseCase
import com.teckzi.rickandmorty.domain.use_cases.location_use_cases.GetAllLocationUseCase
import com.teckzi.rickandmorty.domain.use_cases.location_use_cases.GetSearchedLocationUseCase
import com.teckzi.rickandmorty.domain.use_cases.location_use_cases.GetSelectedLocationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            getAllCharactersUseCase = GetAllCharactersUseCase(repository),
            getSelectedCharacterUseCase = GetSelectedCharacterUseCase(repository),
            getSearchedCharacterUseCase = GetSearchedCharacterUseCase(repository),
            getAllEpisodeUseCase = GetAllEpisodeUseCase(repository),
            getSelectedEpisodeUseCase = GetSelectedEpisodeUseCase(repository),
            getSearchedEpisodeUseCase = GetSearchedEpisodeUseCase(repository),
            getAllLocationUseCase = GetAllLocationUseCase(repository),
            getSelectedLocationUseCase = GetSelectedLocationUseCase(repository),
            getSearchedLocationUseCase = GetSearchedLocationUseCase(repository)
        )
    }
}