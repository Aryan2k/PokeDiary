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

    /**
     * Fetches a list of Pokémon from the API with the given offset and limit.
     *
     * @param offset The starting point for the list of Pokémon to retrieve. If null, an empty string is used.
     * @param limit The maximum number of Pokémon to retrieve. If null, an empty string is used.
     * @return A [ResponseState] object containing either a successful result with [PokemonListContent] or an error message.
     */
    override suspend fun getPokemonList(offset: String?, limit: String?): ResponseState<PokemonListContent?> {
        return try {
            val response = pokemonApi.getPokemonList(offset.orEmpty(), limit.orEmpty())
            val responseData = response.body()
            if (response.isSuccessful && responseData != null) {
                ResponseState.success(responseData.toPokemonListContent())
            } else {
                ResponseState.error(response.message())
            }
        } catch (e: Exception) {
            ResponseState.error(e.message ?: "An unknown error occurred")
        }
    }

    /**
     * Fetches the details of a specific Pokémon from the API using its ID.
     *
     * @param id The unique identifier of the Pokémon to retrieve details for.
     * @return A [ResponseState] object containing either a successful result with [PokemonDetailsContent] or an error message.
     */
    override suspend fun getPokemonDetails(id: String): ResponseState<PokemonDetailsContent> {
        return try {
            val response = pokemonApi.getPokemonDetails(id)
            val responseData = response.body()
            if (response.isSuccessful && responseData != null) {
                ResponseState.success(responseData.toPokemonDetailsContent())
            } else {
                ResponseState.error(response.message())
            }
        } catch (e: Exception) {
            ResponseState.error(e.message ?: "An unknown error occurred")
        }
    }

}