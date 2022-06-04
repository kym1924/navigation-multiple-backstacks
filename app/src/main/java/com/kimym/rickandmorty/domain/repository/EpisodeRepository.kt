package com.kimym.rickandmorty.domain.repository

import androidx.paging.PagingData
import com.kimym.rickandmorty.data.entity.Episode
import com.kimym.rickandmorty.util.Resource
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {
    fun getEpisodes(): Flow<PagingData<Episode.EpisodeEntity>>

    fun getEpisodeByUrl(urls: List<String>): Flow<Resource<List<Episode.EpisodeEntity>>>
}
