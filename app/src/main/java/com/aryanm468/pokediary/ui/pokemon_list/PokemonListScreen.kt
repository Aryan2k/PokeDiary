package com.aryanm468.pokediary.ui.pokemon_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aryanm468.base.utils.RequestStatusEnum
import com.aryanm468.pokediary.R
import com.aryanm468.pokediary.beans.pokemon_list.Result
import com.aryanm468.pokediary.ui.common.AppTopAppBar
import com.aryanm468.pokediary.ui.common.PokeBallLoading
import com.aryanm468.pokediary.ui.home.PokemonDetailDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<PokemonListViewModel>()
    val snackBarHostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackBarHostState) },
        topBar = {
            AppTopAppBar(titleText = stringResource(R.string.pokedex))
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            HandleGetPokemonListState(viewModel, navController)
        }
    }
    LaunchedEffect(key1 = Unit) {
        viewModel.getPokemonList()
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
private fun HandleGetPokemonListState(viewModel: PokemonListViewModel, navController: NavHostController) {
    val getPokemonList by viewModel.getPokemonListStateFlow.collectAsState()
    when (getPokemonList.status) {
        RequestStatusEnum.Success -> {
            if (viewModel.pokemonList.isNotEmpty()) {
                PokemonListSection(viewModel, navController)
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
            viewModel.snackBarMessageState.value = getPokemonList.message.orEmpty()
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
private fun PokemonListSection(viewModel: PokemonListViewModel, navController: NavHostController) {
    val listState = rememberLazyListState()
    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo }
            .collect { layoutInfo ->
                val visibleItemsInfo = layoutInfo.visibleItemsInfo
                if (visibleItemsInfo.isNotEmpty()) {
                    val lastVisibleItem = visibleItemsInfo.lastOrNull()
                    if (lastVisibleItem?.index == viewModel.pokemonList.lastIndex) {
                        viewModel.getPokemonList()
                    }
                }
            }
    }
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            items(viewModel.pokemonList) {
                PokemonListItem(it, navController)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        if (viewModel.showLoadingSectionState.value) {
            PokeBallLoading(isFromPageLoading = true)
        }
    }
}

@Composable
private fun PokemonListItem(pokemonListItem: Result, navController: NavHostController) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable { navController.navigate(PokemonDetailDestination(pokemonListItem.url)) }) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = pokemonListItem.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
