package com.aryanm468.data.data_source.remote

import com.aryanm468.data.entities.pokemon_details.PokemonDetailsEntity
import com.aryanm468.data.entities.pokemon_list.PokemonListEntity
import com.aryanm468.data.utils.ApiEndPointsHelper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET(ApiEndPointsHelper.POKE_API_POKEMON_LIST_ENDPOINT)
    suspend fun getPokemonList(
        @Query("offset") offset: String,
        @Query("limit") limit: String,
    ): Response<PokemonListEntity?>

    @GET(ApiEndPointsHelper.POKE_API_POKEMON_DETAILS_ENDPOINT)
    suspend fun getPokemonDetails(
        @Path("id") id: String
    ): Response<PokemonDetailsEntity?>

}