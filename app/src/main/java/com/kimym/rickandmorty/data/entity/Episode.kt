package com.kimym.rickandmorty.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class Episode(
    @SerializedName("results")
    val results: List<EpisodeEntity>,
) {
    @Parcelize
    data class EpisodeEntity(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("air_date")
        val date: String,
        @SerializedName("episode")
        val episode: String,
        @SerializedName("characters")
        val characters: List<String>,
    ) : Parcelable {
        override fun toString(): String {
            return "# $id"
        }
    }
}
