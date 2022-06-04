package com.kimym.rickandmorty.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kimym.rickandmorty.data.api.EpisodeService
import com.kimym.rickandmorty.data.entity.Episode
import com.kimym.rickandmorty.data.paging.RickAndMortyPagingSource
import com.kimym.rickandmorty.domain.repository.EpisodeRepository
import com.kimym.rickandmorty.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject

@ExperimentalPagingApi
class EpisodeRepositoryImpl @Inject constructor(
    private val service: EpisodeService,
) : EpisodeRepository {
    override fun getEpisodes(): Flow<PagingData<Episode.EpisodeEntity>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { RickAndMortyPagingSource { page -> service.getEpisodes(page).results } }
        ).flow
    }

    override fun getEpisodeByUrl(urls: List<String>): Flow<Resource<List<Episode.EpisodeEntity>>> {
        return flow {
            val episodes = urls.map { url ->
                val id = url.split("/").last().toInt()
                service.getEpisodeById(id)
            }
            emit(Resource.success(episodes))
        }.onStart {
            emit(Resource.loading(null))
        }.catch { e ->
            emit(Resource.error(e.toString(), null))
            Timber.d(e.toString())
        }
    }
}
