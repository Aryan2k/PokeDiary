package com.aryanm468.pokediary.beans.pokemon_details

data class PokemonDetailsBean(
    val abilities: List<Ability>,
    val height: Int,
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
)