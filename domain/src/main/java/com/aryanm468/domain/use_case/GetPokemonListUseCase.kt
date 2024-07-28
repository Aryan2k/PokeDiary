package com.aryanm468.domain.use_case

import com.aryanm468.base.utils.ResponseState
import com.aryanm468.domain.contents.pokemon_list.PokemonListContent
import com.aryanm468.domain.repository.IPokemonRepository
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(private val pokemonRepository: IPokemonRepository) {
    suspend operator fun invoke(offset: String?, limit: String?): ResponseState<PokemonListContent?> {
        return pokemonRepository.getPokemonList(offset, limit)
    }
}