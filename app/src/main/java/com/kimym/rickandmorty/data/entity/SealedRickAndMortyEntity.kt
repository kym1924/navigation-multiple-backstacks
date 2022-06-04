package com.kimym.rickandmorty.data.entity

sealed class SealedRickAndMortyEntity {
    data class CharacterEntity(val entity: Character.CharacterEntity) : SealedRickAndMortyEntity() {
        override val id: Int
            get() = entity.id
    }

    data class LocationEntity(val entity: Location.LocationEntity) : SealedRickAndMortyEntity() {
        override val id: Int
            get() = entity.id
    }

    data class EpisodeEntity(val entity: Episode.EpisodeEntity) : SealedRickAndMortyEntity() {
        override val id: Int
            get() = entity.id
    }

    abstract val id: Int
}
