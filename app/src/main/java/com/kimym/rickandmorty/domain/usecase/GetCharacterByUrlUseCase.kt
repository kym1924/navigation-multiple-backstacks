package com.kimym.rickandmorty.domain.usecase

import com.kimym.rickandmorty.data.entity.Character
import com.kimym.rickandmorty.domain.repository.CharacterRepository
import com.kimym.rickandmorty.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterByUrlUseCase @Inject constructor(
    private val repository: CharacterRepository,
) {
    operator fun invoke(urls: List<String>): Flow<Resource<List<Character.CharacterEntity>>> {
        return repository.getCharacterByUrl(urls)
    }
}
