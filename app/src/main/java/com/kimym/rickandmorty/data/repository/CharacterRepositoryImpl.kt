package com.kimym.rickandmorty.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kimym.rickandmorty.data.api.CharacterService
import com.kimym.rickandmorty.data.entity.Character
import com.kimym.rickandmorty.data.paging.RickAndMortyPagingSource
import com.kimym.rickandmorty.domain.repository.CharacterRepository
import com.kimym.rickandmorty.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject

@ExperimentalPagingApi
class CharacterRepositoryImpl @Inject constructor(
    private val service: CharacterService,
    private val dispatcherIO: CoroutineDispatcher,
) : CharacterRepository {
    override fun getCharacters(): Flow<PagingData<Character.CharacterEntity>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { RickAndMortyPagingSource { page -> service.getCharacters(page).results } }
        ).flow
    }

    override fun getCharacterByUrl(urls: List<String>): Flow<Resource<List<Character.CharacterEntity>>> {
        return flow {
            val characters = urls.map { url ->
                val id = url.split("/").last().toInt()
                service.getCharacterById(id)
            }
            emit(Resource.success(characters))
        }.onStart {
            emit(Resource.loading(null))
        }.catch { e ->
            emit(Resource.error(e.toString(), null))
            Timber.d(e.toString())
        }.flowOn(dispatcherIO)
    }
}
