package com.kimym.rickandmorty.data.api

import com.kimym.rickandmorty.data.entity.Character
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): Character

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): Character.CharacterEntity
}
