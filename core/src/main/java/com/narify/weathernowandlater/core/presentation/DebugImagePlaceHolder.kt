package com.narify.weathernowandlater.core.presentation

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource

@Composable
fun debugPainterPlaceHolder(@DrawableRes imageRes: Int): Painter? {
    return if (LocalInspectionMode.current) painterResource(imageRes)
    else null
}
