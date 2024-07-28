package com.aryanm468.data.entities.pokemon_list

data class PokemonListEntity(
    val count: Int,
    val next: String,
    val previous: Any?,
    val results: List<Result>
)