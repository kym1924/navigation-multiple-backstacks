package com.kimym.rickandmorty.presentation.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kimym.rickandmorty.domain.usecase.GetLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    useCase: GetLocationsUseCase,
) : ViewModel() {
    val locations = useCase().cachedIn(viewModelScope)

    override fun onCleared() {
        super.onCleared()
        Timber.d("onCleared")
    }
}
