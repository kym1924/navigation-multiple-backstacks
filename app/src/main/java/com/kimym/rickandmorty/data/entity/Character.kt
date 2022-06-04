package com.kimym.rickandmorty.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class Character(
    @SerializedName("results")
    val results: List<CharacterEntity>,
) {
    @Parcelize
    data class CharacterEntity(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("status")
        val status: String,
        @SerializedName("species")
        val species: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("gender")
        val gender: String,
        @SerializedName("origin")
        val origin: Location,
        @SerializedName("location")
        val location: Location,
        @SerializedName("image")
        val image: String,
        @SerializedName("episode")
        val episode: List<String>,
    ) : Parcelable {
        @Parcelize
        data class Location(
            @SerializedName("name")
            val name: String,
            @SerializedName("url")
            val url: String,
        ) : Parcelable

        override fun toString(): String {
            return "# $id"
        }
    }
}
