package com.kimym.rickandmorty.domain.usecase

import com.kimym.rickandmorty.data.entity.Episode
import com.kimym.rickandmorty.domain.repository.EpisodeRepository
import com.kimym.rickandmorty.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEpisodeByUrlUseCase @Inject constructor(
    private val repository: EpisodeRepository,
) {
    operator fun invoke(urls: List<String>): Flow<Resource<List<Episode.EpisodeEntity>>> {
        return repository.getEpisodeByUrl(urls)
    }
}
