package com.kimym.rickandmorty.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class Location(
    @SerializedName("results")
    val results: List<LocationEntity>,
) {
    @Parcelize
    data class LocationEntity(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("dimension")
        val dimension: String,
        @SerializedName("residents")
        val residents: List<String>,
    ) : Parcelable {
        override fun toString(): String {
            return "# $id"
        }
    }
}
