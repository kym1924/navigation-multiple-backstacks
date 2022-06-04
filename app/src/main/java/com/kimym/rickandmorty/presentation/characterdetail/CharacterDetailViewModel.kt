package com.kimym.rickandmorty.presentation.characterdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimym.rickandmorty.data.entity.Character
import com.kimym.rickandmorty.domain.usecase.GetEpisodeByUrlUseCase
import com.kimym.rickandmorty.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getEpisodeByUrlUseCase: GetEpisodeByUrlUseCase,
) : ViewModel() {
    private val _character = MutableStateFlow<Character.CharacterEntity?>(null)
    val character = _character.asStateFlow()

    val episodes = _character.filterNotNull().flatMapLatest { entity ->
        getEpisodeByUrlUseCase(entity.episode)
    }.stateIn(viewModelScope, SharingStarted.Lazily, Resource.loading(null))

    fun initCharacter(character: Character.CharacterEntity) {
        _character.value = character
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("onCleared")
    }
}
