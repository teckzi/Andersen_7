package com.teckzi.rickandmorty.domain.use_cases.episode_use_cases

import androidx.paging.PagingData
import com.teckzi.rickandmorty.domain.model.EpisodeModel
import com.teckzi.rickandmorty.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow

class GetAllEpisodeUseCase(
    private val repository: IRepository
) {
    operator fun invoke(): Flow<PagingData<EpisodeModel>> {
        return repository.getAllEpisodes()
    }
}