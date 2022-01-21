package com.teckzi.rickandmorty.domain.use_cases.episode_use_cases

import com.teckzi.rickandmorty.domain.model.EpisodeModel
import com.teckzi.rickandmorty.domain.repository.IRepository

class GetEpisodeListById(
    private val repository: IRepository
) {
    suspend operator fun invoke(episodeIdList: List<Int>): List<EpisodeModel> {
        return repository.getEpisodeListById(episodeIdList)
    }
}