package com.kimym.rickandmorty.data.api

import com.kimym.rickandmorty.data.entity.Location
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationService {
    @GET("location")
    suspend fun getLocations(
        @Query("page") page: Int,
    ): Location
}
