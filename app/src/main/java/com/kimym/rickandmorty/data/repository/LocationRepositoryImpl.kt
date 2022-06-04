package com.kimym.rickandmorty.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kimym.rickandmorty.data.api.LocationService
import com.kimym.rickandmorty.data.entity.Location
import com.kimym.rickandmorty.data.paging.RickAndMortyPagingSource
import com.kimym.rickandmorty.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
class LocationRepositoryImpl @Inject constructor(
    private val service: LocationService,
) : LocationRepository {
    override fun getLocations(): Flow<PagingData<Location.LocationEntity>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { RickAndMortyPagingSource { page -> service.getLocations(page).results } }
        ).flow
    }
}
