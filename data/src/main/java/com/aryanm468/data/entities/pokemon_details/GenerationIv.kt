package com.aryanm468.data.entities.pokemon_details

import com.google.gson.annotations.SerializedName

data class GenerationIv(
    @SerializedName("diamond-pearl") val diamondPearl: DiamondPearl,
    @SerializedName("heartgold-soulsilver") val heartgoldSoulsilver: HeartgoldSoulsilver,
    val platinum: Platinum
)
