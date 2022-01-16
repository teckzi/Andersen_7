package com.teckzi.rickandmorty.domain.model.remote_keys

data class CharacterRemoteKeys(
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)