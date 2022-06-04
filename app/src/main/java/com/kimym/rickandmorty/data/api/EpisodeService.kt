package com.kimym.rickandmorty.data.api

import com.kimym.rickandmorty.data.entity.Episode
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodeService {
    @GET("episode")
    suspend fun getEpisodes(
        @Query("page") page: Int
    ): Episode

    @GET("episode/{id}")
    suspend fun getEpisodeById(
        @Path("id") id: Int
    ): Episode.EpisodeEntity
}
