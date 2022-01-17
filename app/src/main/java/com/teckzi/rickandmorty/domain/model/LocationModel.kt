package com.teckzi.rickandmorty.domain.model

data class LocationModel(
    var id: Int,
    var name: String,
    var type: String,
    var dimension: String,
    var characters: List<Int>
)