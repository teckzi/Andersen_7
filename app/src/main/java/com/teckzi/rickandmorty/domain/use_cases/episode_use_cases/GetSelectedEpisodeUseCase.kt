package com.teckzi.rickandmorty.domain.use_cases.episode_use_cases

import com.teckzi.rickandmorty.domain.model.EpisodeModel
import com.teckzi.rickandmorty.domain.repository.IRepository

class GetSelectedEpisodeUseCase(
    private val repository: IRepository
) {
    suspend operator fun invoke(episodeId: Int): EpisodeModel {
        return repository.getSelectedEpisode(episodeId)
    }
}