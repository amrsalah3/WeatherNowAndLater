package com.narify.weathernowandlater.core.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EmptyContent(
    message: String,
    modifier: Modifier = Modifier,
    textColor: Color = Color.Gray,
) {
    Box(modifier.fillMaxSize()) {
        Text(
            text = message,
            color = textColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun EmptyContentPreview() {
    Surface {
        EmptyContent("Empty content")
    }
}
