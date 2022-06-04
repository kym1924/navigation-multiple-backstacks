package com.kimym.rickandmorty.presentation.locationdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimym.rickandmorty.data.entity.Location
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
class LocationDetailViewModel @Inject constructor(
    private val getCharacterByUrlUseCase: GetCharacterByUrlUseCase,
) : ViewModel() {
    private val _location = MutableStateFlow<Location.LocationEntity?>(null)
    val location = _location.asStateFlow()

    val residents = _location.filterNotNull().flatMapLatest { entity ->
        getCharacterByUrlUseCase(entity.residents)
    }.stateIn(viewModelScope, SharingStarted.Lazily, Resource.loading(null))

    fun initLocation(location: Location.LocationEntity) {
        _location.value = location
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("onCleared")
    }
}
