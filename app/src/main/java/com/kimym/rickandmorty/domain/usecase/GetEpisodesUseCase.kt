package com.kimym.rickandmorty.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.kimym.rickandmorty.data.entity.SealedRickAndMortyEntity
import com.kimym.rickandmorty.domain.repository.EpisodeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetEpisodesUseCase @Inject constructor(
    private val repository: EpisodeRepository,
) {
    operator fun invoke(): Flow<PagingData<SealedRickAndMortyEntity>> {
        return repository.getEpisodes().map { pagingData ->
            pagingData.map { entity ->
                SealedRickAndMortyEntity.EpisodeEntity(entity)
            }
        }
    }
}
