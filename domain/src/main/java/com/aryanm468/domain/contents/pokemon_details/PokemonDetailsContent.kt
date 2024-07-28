package com.aryanm468.domain.contents.pokemon_details

data class PokemonDetailsContent(
    val abilities: List<Ability>,
    val height: Int,
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
)