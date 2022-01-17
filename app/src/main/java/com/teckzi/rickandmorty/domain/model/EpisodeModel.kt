package com.teckzi.rickandmorty.domain.model

data class EpisodeModel(
    var id: Int,
    var name: String,
    var air_date: String,
    var episode: String,
    var characters: List<Int>
)