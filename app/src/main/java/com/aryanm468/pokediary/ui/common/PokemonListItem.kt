package com.aryanm468.pokediary.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aryanm468.pokediary.beans.pokemon_list.Result
import com.aryanm468.pokediary.ui.home.PokemonDetailDestination

@Composable
fun PokemonListItem(pokemonListItem: Result, navController: NavHostController) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
        .clickable { navController.navigate(PokemonDetailDestination(pokemonListItem.url)) }) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                text = pokemonListItem.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
