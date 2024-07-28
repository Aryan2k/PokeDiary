package com.aryanm468.pokediary.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.aryanm468.pokediary.base.BaseActivity
import com.aryanm468.pokediary.ui.common.AnimatedNavHost
import com.aryanm468.pokediary.ui.pokemon_detail.PokemonDetailScreen
import com.aryanm468.pokediary.ui.pokemon_list.PokemonListScreen
import com.aryanm468.pokediary.ui.theme.PokeDiaryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokeDiaryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        val navController = rememberNavController()
                        AnimatedNavHost(
                            navController = navController,
                            startDestination = PokemonListDestination
                        ) {
                            composable<PokemonListDestination> {
                                PokemonListScreen(navController = navController)
                            }
                            composable<PokemonDetailDestination> { backStackEntry ->
                                val pokemonDetailsDestination = backStackEntry.toRoute<PokemonDetailDestination>()
                                PokemonDetailScreen(detailsApiUrl = pokemonDetailsDestination.detailsApiUrl, navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}