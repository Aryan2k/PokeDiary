package com.aryanm468.domain.use_case

import com.aryanm468.base.utils.ResponseState
import com.aryanm468.domain.contents.pokemon_details.PokemonDetailsContent
import com.aryanm468.domain.repository.IPokemonRepository
import javax.inject.Inject

class GetPokemonDetailsUseCase @Inject constructor(private val pokemonRepository: IPokemonRepository) {
    suspend operator fun invoke(id: String): ResponseState<PokemonDetailsContent?> {
        return pokemonRepository.getPokemonDetails(id)
    }
}