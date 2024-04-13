package com.narify.weathernowandlater.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun WeatherTopBar(
    title: String,
    onChangeCityClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterStart)
        )
        IconButton(
            onClick = onChangeCityClicked,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterEnd)
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Change your city preference"
            )
        }
    }
}
