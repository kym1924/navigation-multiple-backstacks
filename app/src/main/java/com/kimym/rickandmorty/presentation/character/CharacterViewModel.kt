package com.kimym.rickandmorty.presentation.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kimym.rickandmorty.domain.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    getCharactersUseCase: GetCharactersUseCase,
) : ViewModel() {
    val characters = getCharactersUseCase().cachedIn(viewModelScope)

    override fun onCleared() {
        super.onCleared()
        Timber.d("onCleared")
    }
}
