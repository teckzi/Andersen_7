package com.teckzi.rickandmorty.data.mappers

import com.teckzi.rickandmorty.data.local.model.CharacterDbo
import com.teckzi.rickandmorty.data.network.model.CharacterDto
import com.teckzi.rickandmorty.domain.model.CharacterModel
import com.teckzi.rickandmorty.util.addToIntList
import com.teckzi.rickandmorty.util.getIdFromUrl

fun CharacterDto.toCharacterModel() = CharacterModel(
    id = characterId,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    origin = origin.url.getIdFromUrl(),
    location = location.url.getIdFromUrl(),
    image = image,
    episode = episode.addToIntList()
)

fun CharacterDbo.toCharacterModel() = CharacterModel(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    origin = origin,
    location = location,
    image = image,
    episode = episode
)

fun CharacterModel.toCharacterDbo() = CharacterDbo(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    origin = origin,
    location = location,
    image = image,
    episode = episode
)
