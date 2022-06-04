package com.kimym.rickandmorty.di

import com.kimym.rickandmorty.domain.repository.CharacterRepository
import com.kimym.rickandmorty.domain.repository.EpisodeRepository
import com.kimym.rickandmorty.domain.repository.LocationRepository
import com.kimym.rickandmorty.domain.usecase.GetCharacterByUrlUseCase
import com.kimym.rickandmorty.domain.usecase.GetCharactersUseCase
import com.kimym.rickandmorty.domain.usecase.GetEpisodeByUrlUseCase
import com.kimym.rickandmorty.domain.usecase.GetEpisodesUseCase
import com.kimym.rickandmorty.domain.usecase.GetLocationsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    @ViewModelScoped
    fun provideGetCharactersUseCase(repository: CharacterRepository): GetCharactersUseCase {
        return GetCharactersUseCase(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetCharacterByUrlUseCase(repository: CharacterRepository): GetCharacterByUrlUseCase {
        return GetCharacterByUrlUseCase(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetLocationsUseCase(repository: LocationRepository): GetLocationsUseCase {
        return GetLocationsUseCase(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetEpisodesUseCase(repository: EpisodeRepository): GetEpisodesUseCase {
        return GetEpisodesUseCase(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetEpisodeByUrlUseCase(repository: EpisodeRepository): GetEpisodeByUrlUseCase {
        return GetEpisodeByUrlUseCase(repository)
    }
}
