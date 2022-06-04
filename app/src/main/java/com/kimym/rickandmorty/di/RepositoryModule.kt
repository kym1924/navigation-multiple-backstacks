package com.kimym.rickandmorty.di

import androidx.paging.ExperimentalPagingApi
import com.kimym.rickandmorty.data.api.CharacterService
import com.kimym.rickandmorty.data.api.EpisodeService
import com.kimym.rickandmorty.data.api.LocationService
import com.kimym.rickandmorty.data.repository.CharacterRepositoryImpl
import com.kimym.rickandmorty.data.repository.EpisodeRepositoryImpl
import com.kimym.rickandmorty.data.repository.LocationRepositoryImpl
import com.kimym.rickandmorty.domain.repository.CharacterRepository
import com.kimym.rickandmorty.domain.repository.EpisodeRepository
import com.kimym.rickandmorty.domain.repository.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@ExperimentalPagingApi
@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideCharacterRepository(
        service: CharacterService,
        dispatchersIO: CoroutineDispatcher,
    ): CharacterRepository {
        return CharacterRepositoryImpl(service, dispatchersIO)
    }

    @Provides
    @ViewModelScoped
    fun provideLocationRepository(service: LocationService): LocationRepository {
        return LocationRepositoryImpl(service)
    }

    @Provides
    @ViewModelScoped
    fun provideEpisodeRepository(service: EpisodeService): EpisodeRepository {
        return EpisodeRepositoryImpl(service)
    }
}
