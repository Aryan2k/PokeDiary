package com.aryanm468.pokediary.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.aryanm468.pokediary.R

@Composable
fun PokeBallLoading(isFromPageLoading: Boolean = false) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.pokeball_animation))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = true,
        iterations = LottieConstants.IterateForever
    )
    Box(
        modifier = if (isFromPageLoading) Modifier.fillMaxWidth() else Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            modifier = if (isFromPageLoading) Modifier.size(60.dp) else Modifier.size((screenWidth * 0.35f).dp),
            composition = composition,
            progress = { progress }
        )
    }
}
