package com.aryanm468.pokediary.mappers

import com.aryanm468.domain.contents.pokemon_list.PokemonListContent
import com.aryanm468.pokediary.beans.pokemon_details.Ability
import com.aryanm468.pokediary.beans.pokemon_details.AbilityX
import com.aryanm468.pokediary.beans.pokemon_details.PokemonDetailsBean
import com.aryanm468.pokediary.beans.pokemon_details.Sprites
import com.aryanm468.pokediary.beans.pokemon_details.Stat
import com.aryanm468.pokediary.beans.pokemon_details.StatX
import com.aryanm468.pokediary.beans.pokemon_details.Type
import com.aryanm468.pokediary.beans.pokemon_details.TypeX
import com.aryanm468.pokediary.beans.pokemon_list.PokemonListBean
import com.aryanm468.pokediary.beans.pokemon_list.Result

object AppMappers {
    fun PokemonListContent.toPokemonListBean(): PokemonListBean {
        return PokemonListBean(
            count = this.count,
            next = this.next,
            previous = this.previous,
            results = this.results.map { it.toPokemonListBeanResult() }
        )
    }

    private fun com.aryanm468.domain.contents.pokemon_list.Result.toPokemonListBeanResult(): Result {
        return Result(
            name = this.name.replaceFirstChar { it.titlecase() },
            url = this.url
        )
    }

    fun com.aryanm468.domain.contents.pokemon_details.PokemonDetailsContent.toPokemonDetailsBean(): PokemonDetailsBean {
        return PokemonDetailsBean(
            abilities = this.abilities.map { it.toPokemonDetailsBeanAbility() },
            height = this.height,
            id = this.id,
            name = this.name.replaceFirstChar { it.titlecase() },
            sprites = this.sprites.toPokemonDetailsBeanSprites(),
            stats = this.stats.map { it.toPokemonDetailsBeanStat() },
            types = this.types.map { it.toPokemonDetailsBeanType() },
            weight = this.weight
        )
    }

    private fun com.aryanm468.domain.contents.pokemon_details.Ability.toPokemonDetailsBeanAbility(): Ability {
        return Ability(
            ability = this.ability.toPokemonDetailsBeanAbilityX(),
            is_hidden = this.is_hidden
        )
    }

    private fun com.aryanm468.domain.contents.pokemon_details.AbilityX.toPokemonDetailsBeanAbilityX(): AbilityX {
        return AbilityX(
            name = this.name.replaceFirstChar { it.titlecase() }
        )
    }

    private fun com.aryanm468.domain.contents.pokemon_details.Sprites.toPokemonDetailsBeanSprites(): Sprites {
        return Sprites(
            front_default = this.front_default,
            front_female = this.front_female,
            front_shiny = this.front_shiny,
            front_shiny_female = this.front_shiny_female
        )
    }

    private fun com.aryanm468.domain.contents.pokemon_details.Stat.toPokemonDetailsBeanStat(): Stat {
        return Stat(
            base_stat = this.base_stat,
            stat = this.stat.toPokemonDetailsBeanStatX()
        )
    }

    private fun com.aryanm468.domain.contents.pokemon_details.StatX.toPokemonDetailsBeanStatX(): StatX {
        return StatX(
            name = this.name.replaceFirstChar { it.titlecase() }
        )
    }

    private fun com.aryanm468.domain.contents.pokemon_details.Type.toPokemonDetailsBeanType(): Type {
        return Type(
            slot = this.slot,
            type = this.type.toPokemonDetailsBeanTypeX()
        )
    }

    private fun com.aryanm468.domain.contents.pokemon_details.TypeX.toPokemonDetailsBeanTypeX(): TypeX {
        return TypeX(
            name = this.name.replaceFirstChar { it.titlecase() }
        )
    }
}