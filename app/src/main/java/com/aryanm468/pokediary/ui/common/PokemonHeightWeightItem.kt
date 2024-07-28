package com.aryanm468.pokediary.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aryanm468.pokediary.utils.ColorHelper

@Composable
fun PokemonHeightWeightItem(headingText: String, valueText: String) {
    Column {
        Text(
            text = headingText,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = ColorHelper.gray()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = valueText,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}