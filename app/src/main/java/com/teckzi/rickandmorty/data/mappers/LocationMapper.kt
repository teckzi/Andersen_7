package com.teckzi.rickandmorty.data.mappers

import com.teckzi.rickandmorty.domain.model.LocationModel
import com.teckzi.rickandmorty.util.addToIntList
import com.teckzi.ricks.data.local.model.LocationDbo
import com.teckzi.ricks.data.network.model.LocationDto

fun LocationDto.toLocationModel() = LocationModel(
    id = id,
    name = name,
    dimension = dimension,
    type = type,
    characters = characters.addToIntList()
)

fun LocationDbo.toLocationModel() = LocationModel(
    id = id,
    name = name,
    dimension = dimension,
    type = type,
    characters = characters
)

fun LocationModel.toLocationDbo() = LocationDbo(
    id = id,
    name = name,
    dimension = dimension,
    type = type,
    characters = characters
)