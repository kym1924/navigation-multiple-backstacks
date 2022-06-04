package com.kimym.rickandmorty.presentation.episodedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimym.rickandmorty.data.entity.Episode
import com.kimym.rickandmorty.domain.usecase.GetCharacterByUrlUseCase
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
class EpisodeDetailViewModel @Inject constructor(
    private val getCharacterByUrlUseCase: GetCharacterByUrlUseCase,
) : ViewModel() {
    private val _episode = MutableStateFlow<Episode.EpisodeEntity?>(null)
    val episode = _episode.asStateFlow()

    val characters = _episode.filterNotNull().flatMapLatest { entity ->
        getCharacterByUrlUseCase(entity.characters)
    }.stateIn(viewModelScope, SharingStarted.Lazily, Resource.loading(null))

    fun initEpisode(episode: Episode.EpisodeEntity) {
        _episode.value = episode
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("onCleared")
    }
}
