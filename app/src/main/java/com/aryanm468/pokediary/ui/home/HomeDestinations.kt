package com.aryanm468.pokediary.ui.home

import kotlinx.serialization.Serializable

@Serializable
object PokemonListDestination

@Serializable
data class PokemonDetailDestination(val detailsApiUrl: String)
