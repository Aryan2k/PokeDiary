package com.aryanm468.data.mappers

import com.aryanm468.data.entities.pokemon_details.Ability
import com.aryanm468.data.entities.pokemon_details.AbilityX
import com.aryanm468.data.entities.pokemon_details.Form
import com.aryanm468.data.entities.pokemon_details.PokemonDetailsEntity
import com.aryanm468.data.entities.pokemon_details.Sprites
import com.aryanm468.data.entities.pokemon_details.Stat
import com.aryanm468.data.entities.pokemon_details.StatX
import com.aryanm468.data.entities.pokemon_details.Type
import com.aryanm468.data.entities.pokemon_details.TypeX
import com.aryanm468.data.entities.pokemon_list.PokemonListEntity
import com.aryanm468.data.entities.pokemon_list.Result
import com.aryanm468.domain.contents.pokemon_details.PokemonDetailsContent
import com.aryanm468.domain.contents.pokemon_list.PokemonListContent

object DataMappers {
    fun PokemonListEntity.toPokemonListContent(): PokemonListContent {
        return PokemonListContent(
            count = this.count,
            next = this.next,
            previous = this.previous,
            results = this.results.map { it.toPokemonListContentResult() }
        )
    }

    private fun Result.toPokemonListContentResult(): com.aryanm468.domain.contents.pokemon_list.Result {
        return com.aryanm468.domain.contents.pokemon_list.Result(
            name = this.name,
            url = this.url
        )
    }

    fun PokemonDetailsEntity.toPokemonDetailsContent(): PokemonDetailsContent {
        return PokemonDetailsContent(
            abilities = this.abilities.map { it.toPokemonDetailsContentAbility() },
            height = this.height,
            id = this.id,
            name = this.name,
            sprites = this.sprites.toPokemonDetailsContentSprites(),
            stats = this.stats.map { it.toPokemonDetailsContentStat() },
            types = this.types.map { it.toPokemonDetailsContentType() },
            weight = this.weight
        )
    }

    private fun Ability.toPokemonDetailsContentAbility(): com.aryanm468.domain.contents.pokemon_details.Ability {
        return com.aryanm468.domain.contents.pokemon_details.Ability(
            ability = this.ability.toPokemonDetailsContentAbilityX(),
            is_hidden = this.is_hidden
        )
    }

    private fun AbilityX.toPokemonDetailsContentAbilityX(): com.aryanm468.domain.contents.pokemon_details.AbilityX {
        return com.aryanm468.domain.contents.pokemon_details.AbilityX(
            name = this.name
        )
    }

    private fun Sprites.toPokemonDetailsContentSprites(): com.aryanm468.domain.contents.pokemon_details.Sprites {
        return com.aryanm468.domain.contents.pokemon_details.Sprites(
            front_default = this.front_default,
            front_female = this.front_female,
            front_shiny = this.front_shiny,
            front_shiny_female = this.front_shiny_female
        )
    }

    private fun Stat.toPokemonDetailsContentStat(): com.aryanm468.domain.contents.pokemon_details.Stat {
        return com.aryanm468.domain.contents.pokemon_details.Stat(
            base_stat = this.base_stat,
            stat = this.stat.toPokemonDetailsContentStatX()
        )
    }

    private fun StatX.toPokemonDetailsContentStatX(): com.aryanm468.domain.contents.pokemon_details.StatX {
        return com.aryanm468.domain.contents.pokemon_details.StatX(
            name = this.name
        )
    }

    private fun Type.toPokemonDetailsContentType(): com.aryanm468.domain.contents.pokemon_details.Type {
        return com.aryanm468.domain.contents.pokemon_details.Type(
            slot = this.slot,
            type = this.type.toPokemonDetailsContentTypeX()
        )
    }

    private fun TypeX.toPokemonDetailsContentTypeX(): com.aryanm468.domain.contents.pokemon_details.TypeX {
        return com.aryanm468.domain.contents.pokemon_details.TypeX(
            name = this.name
        )
    }
}