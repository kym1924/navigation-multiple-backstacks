package com.kimym.rickandmorty.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.kimym.rickandmorty.data.entity.SealedRickAndMortyEntity
import com.kimym.rickandmorty.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository,
) {
    operator fun invoke(): Flow<PagingData<SealedRickAndMortyEntity>> {
        return repository.getCharacters().map { pagingData ->
            pagingData.map { character ->
                SealedRickAndMortyEntity.CharacterEntity(character)
            }
        }
    }
}
