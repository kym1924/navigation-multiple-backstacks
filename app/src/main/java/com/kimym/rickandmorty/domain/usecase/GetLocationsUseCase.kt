package com.kimym.rickandmorty.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.kimym.rickandmorty.data.entity.SealedRickAndMortyEntity
import com.kimym.rickandmorty.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetLocationsUseCase @Inject constructor(
    private val repository: LocationRepository,
) {
    operator fun invoke(): Flow<PagingData<SealedRickAndMortyEntity>> {
        return repository.getLocations().map { pagingData ->
            pagingData.map { entity ->
                SealedRickAndMortyEntity.LocationEntity(entity)
            }
        }
    }
}
