package com.aryanm468.pokediary.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PokemonChip(modifier: Modifier = Modifier, text: String, cardBackgroundColor: Color = Color.White.copy(alpha = 0.3f)) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .wrapContentSize()
            .background(color = Color.Transparent),
        colors = CardDefaults.cardColors(containerColor = cardBackgroundColor)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
            fontWeight = FontWeight.SemiBold
        )
    }
}