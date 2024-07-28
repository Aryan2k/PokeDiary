package com.aryanm468.pokediary.ui.pokemon_list

import androidx.compose.runtime.mutableStateOf
import com.aryanm468.base.utils.RequestStatusEnum
import com.aryanm468.base.utils.ResponseState
import com.aryanm468.domain.use_case.GetPokemonListUseCase
import com.aryanm468.pokediary.base.BaseViewModel
import com.aryanm468.pokediary.beans.pokemon_list.PokemonListBean
import com.aryanm468.pokediary.beans.pokemon_list.Result
import com.aryanm468.pokediary.mappers.AppMappers.toPokemonListBean
import com.aryanm468.pokediary.utils.AppFunctionHelper
import com.aryanm468.pokediary.utils.QueryParamsHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(private val getPokemonListUseCase: GetPokemonListUseCase) : BaseViewModel() {

    private var nextUrl = ""

    val snackBarMessageState = mutableStateOf("")
    val showLoadingSectionState = mutableStateOf(false)
    val pokemonList: MutableList<Result> = mutableListOf()
    private var currentPokemonListJob: Job? = null

    private var _getPokemonListStateFlow: MutableStateFlow<ResponseState<PokemonListBean>> = MutableStateFlow(ResponseState.none())
    val getPokemonListStateFlow = _getPokemonListStateFlow.asStateFlow()

    fun getPokemonList() {
        currentPokemonListJob?.cancel()
        currentPokemonListJob = backgroundCall {
            if (nextUrl.isBlank()) {
                _getPokemonListStateFlow.value = ResponseState.loading()
            } else {
                withContext(Dispatchers.Main) {
                    showLoadingSectionState.value = true
                }
            }
            val offset = AppFunctionHelper.getQueryParam(nextUrl, QueryParamsHelper.offset)
            val limit = AppFunctionHelper.getQueryParam(nextUrl, QueryParamsHelper.limit)
            val response = getPokemonListUseCase(offset, limit)
            val responseData = response.data
            withContext(Dispatchers.Main) {
                showLoadingSectionState.value = false
            }
            if (response.status == RequestStatusEnum.Success && responseData != null) {
                nextUrl = responseData.next
                pokemonList.addAll(responseData.toPokemonListBean().results)
                _getPokemonListStateFlow.value = ResponseState.success(responseData.toPokemonListBean())
            } else {
                _getPokemonListStateFlow.value = ResponseState.error(response.message ?: "An unknown error occurred")
            }
        }
    }

}