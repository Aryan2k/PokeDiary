package com.aryanm468.pokediary.ui.pokemon_detail

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aryanm468.base.utils.RequestStatusEnum
import com.aryanm468.pokediary.R
import com.aryanm468.pokediary.beans.pokemon_details.Ability
import com.aryanm468.pokediary.beans.pokemon_details.PokemonDetailsBean
import com.aryanm468.pokediary.beans.pokemon_details.Stat
import com.aryanm468.pokediary.ui.common.PokeBallLoading
import com.aryanm468.pokediary.ui.common.PokemonChip
import com.aryanm468.pokediary.ui.common.PokemonHeightWeightItem
import com.aryanm468.pokediary.ui.common.PokemonSpriteItem
import com.aryanm468.pokediary.ui.common.PokemonStatItem
import com.aryanm468.pokediary.utils.AppFunctionHelper
import com.aryanm468.pokediary.utils.ColorHelper

@Composable
fun PokemonDetailScreen(detailsApiUrl: String) {
    val viewModel = hiltViewModel<PokemonDetailViewModel>()
    val snackBarHostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = viewModel.selectedBackgroundColorState.value,
        snackbarHost = { SnackbarHost(snackBarHostState) },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            HandleGetPokemonDetailsState(viewModel)
        }
    }
    LaunchedEffect(key1 = Unit) {
        viewModel.getPokemonDetails(detailsApiUrl)
    }
    LaunchedEffect(key1 = viewModel.snackBarMessageState.value) {
        if (viewModel.snackBarMessageState.value.isNotBlank()) {
            snackBarHostState.showSnackbar(
                viewModel.snackBarMessageState.value,
                duration = SnackbarDuration.Short
            )
            viewModel.snackBarMessageState.value = ""
        }
    }
}

@Composable
private fun HandleGetPokemonDetailsState(viewModel: PokemonDetailViewModel) {
    val getPokemonDetails by viewModel.getPokemonDetailsFlow.collectAsState()
    val getPokemonDetailsData = getPokemonDetails.data
    val isResponseHandled = rememberSaveable { mutableStateOf(false) }

    when (getPokemonDetails.status) {
        RequestStatusEnum.Success -> {
            if (getPokemonDetailsData != null) {
                PokemonDetailsSection(pokemonDetails = getPokemonDetailsData, viewModel = viewModel)
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.no_data_found), fontSize = 18.sp
                    )
                }
            }
        }

        RequestStatusEnum.Exception -> {
            if (!isResponseHandled.value) {
                viewModel.snackBarMessageState.value = getPokemonDetails.message.orEmpty()
                isResponseHandled.value = true
            }
        }

        RequestStatusEnum.Loading -> {
            PokeBallLoading()
        }

        RequestStatusEnum.None -> {
            // No need to handle
        }
    }
}

@Composable
private fun PokemonDetailsSection(pokemonDetails: PokemonDetailsBean, viewModel: PokemonDetailViewModel) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        PokemonBasicDetailsSection(pokemonDetails = pokemonDetails)
        Spacer(modifier = Modifier.height(16.dp))
        PokemonSpritesSection(pokemonDetails = pokemonDetails, viewModel = viewModel, context = context)
        Spacer(modifier = Modifier.height(40.dp))
        PokemonAllDetailsSection(pokemonDetails = pokemonDetails)
    }
}

@Composable
private fun PokemonBasicDetailsSection(pokemonDetails: PokemonDetailsBean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    text = pokemonDetails.name,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = ColorHelper.white()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    pokemonDetails.types.forEach { type ->
                        PokemonChip(text = type.type.name)
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
            Text(
                text = stringResource(R.string.hash) + AppFunctionHelper.addLeadingZeroes(pokemonDetails.id.toString()),
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium,
                color = ColorHelper.white()
            )
        }
    }
}

@Composable
private fun PokemonSpritesSection(pokemonDetails: PokemonDetailsBean, viewModel: PokemonDetailViewModel, context: Context) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (pokemonDetails.sprites.front_default != null) {
            if (!viewModel.isShinySpriteInitialised) {
                viewModel.setBackgroundColor(pokemonDetails.sprites.front_default, context)
                viewModel.isNormalSpriteInitialised = true
            }
            PokemonSpriteItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                sprite = pokemonDetails.sprites.front_default,
                name = pokemonDetails.name,
                form = stringResource(R.string.normal_form),
                cardBackgroundColor = viewModel.normalBackgroundColorState.value
            ) {
                viewModel.selectedBackgroundColorState.value = viewModel.normalBackgroundColorState.value
            }
        }
        if (pokemonDetails.sprites.front_shiny != null) {
            if (!viewModel.isShinySpriteInitialised) {
                viewModel.setShinyBackgroundColor(pokemonDetails.sprites.front_shiny, context)
                viewModel.isShinySpriteInitialised = true
            }
            Spacer(modifier = Modifier.width(8.dp))
            PokemonSpriteItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                sprite = pokemonDetails.sprites.front_shiny,
                name = pokemonDetails.name,
                form = stringResource(R.string.shiny_form),
                cardBackgroundColor = viewModel.shinyBackgroundColorState.value
            ) {
                viewModel.selectedBackgroundColorState.value = viewModel.shinyBackgroundColorState.value
            }
        }
    }
}

@Composable
private fun PokemonAllDetailsSection(pokemonDetails: PokemonDetailsBean) {
    Column(modifier = Modifier.fillMaxWidth()) {
        PokemonHeightWeightSection(modifier = Modifier.align(Alignment.CenterHorizontally), pokemonDetails.height, pokemonDetails.weight)
        Spacer(modifier = Modifier.height(40.dp))
        PokemonAbilitiesSection(pokemonDetails.abilities)
        Spacer(modifier = Modifier.height(30.dp))
        PokemonStatsSection(pokemonDetails.stats)
    }
}

@Composable
private fun PokemonHeightWeightSection(modifier: Modifier = Modifier, height: Int, weight: Int) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .padding(vertical = 12.dp, horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            PokemonHeightWeightItem(headingText = stringResource(R.string.height), valueText = "${height * 10} cm")
            PokemonHeightWeightItem(headingText = stringResource(R.string.weight), valueText = "${weight / 10.0} kg")
        }
    }
}

@Composable
private fun PokemonAbilitiesSection(abilities: List<Ability>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.abilities),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = ColorHelper.white()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            abilities.forEach {
                PokemonChip(text = it.ability.name + if (it.is_hidden) stringResource(R.string.hidden) else "")
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}

@Composable
private fun PokemonStatsSection(stats: List<Stat>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.stats),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = ColorHelper.white()
        )
        Spacer(modifier = Modifier.height(24.dp))
        Column {
            stats.forEach {
                PokemonStatItem(it.stat.name, it.base_stat.toString())
            }
        }
    }
}
