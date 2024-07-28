package com.aryanm468.domain.contents.pokemon_list

data class PokemonListContent(
    val count: Int,
    val next: String,
    val previous: Any?,
    val results: List<Result>
)