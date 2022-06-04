package com.kimym.rickandmorty.presentation.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kimym.rickandmorty.domain.usecase.GetEpisodesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    useCase: GetEpisodesUseCase,
) : ViewModel() {
    val episodes = useCase().cachedIn(viewModelScope)

    override fun onCleared() {
        super.onCleared()
        Timber.d("onCleared")
    }
}
