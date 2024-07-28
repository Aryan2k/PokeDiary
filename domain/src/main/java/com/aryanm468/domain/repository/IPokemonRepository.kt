package com.aryanm468.domain.repository

import com.aryanm468.base.utils.ResponseState
import com.aryanm468.domain.contents.pokemon_details.PokemonDetailsContent
import com.aryanm468.domain.contents.pokemon_list.PokemonListContent

interface IPokemonRepository {

    suspend fun getPokemonList(offset: String?, limit: String?): ResponseState<PokemonListContent?>

    suspend fun getPokemonDetails(id: String): ResponseState<PokemonDetailsContent>

}