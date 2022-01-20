package com.teckzi.rickandmorty.data.mappers

import com.teckzi.rickandmorty.domain.model.EpisodeModel
import com.teckzi.rickandmorty.util.addToIntList
import com.teckzi.ricks.data.local.model.EpisodeDbo
import com.teckzi.ricks.data.network.model.EpisodeDto

fun EpisodeDto.toEpisodeModel() = EpisodeModel(
    id = id,
    name = name,
    airDate = airDate,
    episode = episode,
    characters = characters.addToIntList()
)

fun EpisodeDbo.toEpisodeModel() = EpisodeModel(
    id = id,
    name = name,
    airDate = airDate,
    episode = episode,
    characters = characters
)

fun EpisodeModel.toEpisodeDbo() = EpisodeDbo(
    id = id,
    name = name,
    airDate = airDate,
    episode = episode,
    characters = characters
)