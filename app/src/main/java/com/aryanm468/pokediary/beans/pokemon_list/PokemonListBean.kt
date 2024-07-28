package com.aryanm468.pokediary.beans.pokemon_list

data class PokemonListBean(
    val count: Int,
    val next: String,
    val previous: Any?,
    val results: List<Result>
)
