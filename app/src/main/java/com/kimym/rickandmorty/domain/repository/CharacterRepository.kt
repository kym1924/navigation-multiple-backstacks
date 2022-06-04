package com.kimym.rickandmorty.domain.repository

import androidx.paging.PagingData
import com.kimym.rickandmorty.data.entity.Character
import com.kimym.rickandmorty.util.Resource
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters(): Flow<PagingData<Character.CharacterEntity>>

    fun getCharacterByUrl(urls: List<String>): Flow<Resource<List<Character.CharacterEntity>>>
}
