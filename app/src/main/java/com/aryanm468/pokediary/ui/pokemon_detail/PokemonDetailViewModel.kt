package com.aryanm468.pokediary.ui.pokemon_detail

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.palette.graphics.Palette
import coil.imageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.aryanm468.base.utils.RequestStatusEnum
import com.aryanm468.base.utils.ResponseState
import com.aryanm468.domain.use_case.GetPokemonDetailsUseCase
import com.aryanm468.pokediary.base.BaseViewModel
import com.aryanm468.pokediary.beans.pokemon_details.PokemonDetailsBean
import com.aryanm468.pokediary.mappers.AppMappers.toPokemonDetailsBean
import com.aryanm468.pokediary.utils.AppFunctionHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase) : BaseViewModel() {

    var isNormalSpriteInitialised = false
    var isShinySpriteInitialised = false
    val snackBarMessageState = mutableStateOf("")
    val selectedBackgroundColorState = mutableStateOf(Color.White)
    val normalBackgroundColorState = mutableStateOf(Color.White)
    val shinyBackgroundColorState = mutableStateOf(Color.White)

    private var _getPokemonDetailsFlow: MutableStateFlow<ResponseState<PokemonDetailsBean>> = MutableStateFlow(ResponseState.none())
    val getPokemonDetailsFlow = _getPokemonDetailsFlow.asStateFlow()

    fun getPokemonDetails(detailsApiUrl: String) {
        backgroundCall {
            val pokemonId = AppFunctionHelper.getIdFromUrl(detailsApiUrl)
            if (pokemonId != null) {
                val response = getPokemonDetailsUseCase(pokemonId)
                if (response.status == RequestStatusEnum.Success && response.data != null) {
                    _getPokemonDetailsFlow.value = ResponseState.success(response.data!!.toPokemonDetailsBean())
                } else {
                    _getPokemonDetailsFlow.value = ResponseState.error(response.message ?: "An unknown error occurred")
                }
            } else {
                _getPokemonDetailsFlow.value = ResponseState.error("Cannot fetch pokemon id")
            }
        }
    }

    fun setBackgroundColor(sprite: String, context: Context) {
        backgroundCall {
            val request = ImageRequest.Builder(context)
                .data(sprite)
                .allowHardware(false)
                .build()

            val result = (context.imageLoader.execute(request) as SuccessResult).drawable
            val bitmap = (result as BitmapDrawable).bitmap

            val palette = Palette.from(bitmap).generate()
            val dominantColor = palette.getDominantColor(Color.White.toArgb())
            normalBackgroundColorState.value = Color(dominantColor)
            selectedBackgroundColorState.value = normalBackgroundColorState.value
        }
    }

    fun setShinyBackgroundColor(sprite: String, context: Context) {
        backgroundCall {
            val request = ImageRequest.Builder(context)
                .data(sprite)
                .allowHardware(false)
                .build()

            val result = (context.imageLoader.execute(request) as SuccessResult).drawable
            val bitmap = (result as BitmapDrawable).bitmap

            val palette = Palette.from(bitmap).generate()
            val dominantColor = palette.getDominantColor(Color.White.toArgb())

            shinyBackgroundColorState.value = Color(dominantColor)
        }
    }

}