package com.aryanm468.data.entities.pokemon_details

import com.google.gson.annotations.SerializedName

data class Other(
    @SerializedName("dream_world") val dreamWorld: DreamWorld,
    val home: Home,
    @SerializedName("official-artwork") val officialArtwork: OfficialArtwork,
    val showdown: Showdown
)