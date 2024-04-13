package com.narify.weathernowandlater.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Loading animation for general content.
 */
@Composable
fun LoadingContent(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize()) {
        CircularProgressIndicator(Modifier.align(Alignment.Center))
    }
}

@Composable
private fun LoadingContentPreview() {
    Surface {
        LoadingContent()
    }
}
