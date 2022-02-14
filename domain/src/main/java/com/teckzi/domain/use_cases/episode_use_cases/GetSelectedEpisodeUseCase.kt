package com.teckzi.domain.use_cases.episode_use_cases

import com.teckzi.domain.model.EpisodeModel
import com.teckzi.domain.repository.IRepository

class GetSelectedEpisodeUseCase(
    private val repository: IRepository
) {
    suspend operator fun invoke(episodeId: Int): EpisodeModel {
        return repository.getEpisodeById(episodeId)
    }
}