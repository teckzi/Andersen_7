package com.teckzi.domain.use_cases.episode_use_cases

import com.teckzi.domain.model.EpisodeModel
import com.teckzi.domain.repository.IRepository

class GetEpisodeListById(
    private val repository: IRepository
) {
    suspend operator fun invoke(episodeIdList: List<Int>): List<EpisodeModel> {
        return repository.getEpisodeListById(episodeIdList)
    }
}