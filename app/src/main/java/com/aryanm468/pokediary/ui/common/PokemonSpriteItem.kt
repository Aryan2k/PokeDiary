package com.aryanm468.pokediary.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun PokemonSpriteItem(
    modifier: Modifier = Modifier,
    sprite: String,
    name: String,
    form: String,
    cardBackgroundColor: Color = Color.White,
    formTextColor: Color = Color.White,
    onItemClick: () -> Unit = {}
) {
    Column(modifier = modifier.clickable { onItemClick() }, horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            modifier = Modifier
                .size(150.dp),
            model = sprite,
            contentDescription = name
        )
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(containerColor = cardBackgroundColor)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                text = form,
                fontWeight = FontWeight.Bold,
                color = formTextColor
            )
        }
    }
}
