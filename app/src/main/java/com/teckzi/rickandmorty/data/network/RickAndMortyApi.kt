package com.teckzi.rickandmorty.data.network

import com.teckzi.rickandmorty.data.network.model.ApiResponse
import com.teckzi.rickandmorty.data.network.model.CharacterDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character/")
    suspend fun getCharacters(
        @Query("page") page: Int = 1,
        @Query("name") name: String? = null,
        @Query("status") status: String? = null,
        @Query("species") species: String? = null,
        @Query("type") type: String? = null,
        @Query("gender") gender: String? = null
    ): ApiResponse<CharacterDto>

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): ApiResponse<CharacterDto>

}