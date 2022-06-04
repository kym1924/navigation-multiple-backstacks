package com.kimym.rickandmorty.domain.repository

import androidx.paging.PagingData
import com.kimym.rickandmorty.data.entity.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getLocations(): Flow<PagingData<Location.LocationEntity>>
}
