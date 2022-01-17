package com.teckzi.rickandmorty.domain.use_cases.episode_use_cases

import androidx.paging.PagingData
import com.teckzi.rickandmorty.domain.model.EpisodeModel
import com.teckzi.rickandmorty.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow

class GetSearchedEpisodeUseCase(
    private val repository: IRepository
) {
    suspend operator fun invoke(
        name: String?,
        episode: String?
    ): Flow<PagingData<EpisodeModel>> {
        return repository.searchEpisode(
            name = name,
            episode = episode
        )
    }
}