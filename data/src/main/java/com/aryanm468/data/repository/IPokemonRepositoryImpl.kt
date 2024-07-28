package com.aryanm468.data.repository

import com.aryanm468.base.utils.ResponseState
import com.aryanm468.data.data_source.remote.PokemonApi
import com.aryanm468.data.mappers.DataMappers.toPokemonDetailsContent
import com.aryanm468.data.mappers.DataMappers.toPokemonListContent
import com.aryanm468.domain.contents.pokemon_details.PokemonDetailsContent
import com.aryanm468.domain.contents.pokemon_list.PokemonListContent
import com.aryanm468.domain.repository.IPokemonRepository
import javax.inject.Inject

class IPokemonRepositoryImpl @Inject constructor(private val pokemonApi: PokemonApi) : IPokemonRepository {

    override suspend fun getPokemonList(offset: String?, limit: String?): ResponseState<PokemonListContent?> {
        val response = pokemonApi.getPokemonList(offset.orEmpty(), limit.orEmpty())
        return if (response.isSuccessful && response.body() != null) {
            ResponseState.success(response.body()!!.toPokemonListContent())
        } else {
            ResponseState.error(response.message())
        }
    }

    override suspend fun getPokemonDetails(id: String): ResponseState<PokemonDetailsContent> {
        val response = pokemonApi.getPokemonDetails(id)
        return if (response.isSuccessful && response.body() != null) {
            ResponseState.success(response.body()!!.toPokemonDetailsContent())
        } else {
            ResponseState.error(response.message())
        }
    }

}