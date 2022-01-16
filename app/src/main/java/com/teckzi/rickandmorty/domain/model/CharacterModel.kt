package com.teckzi.rickandmorty.domain.model

data class CharacterModel(
    var id: Int,
    var name: String,
    var status: String,
    var species: String,
    var type: String,
    var gender: String,
    var origin: Int,
    var location: Int,
    var image: String,
    var episode: List<Int>
)