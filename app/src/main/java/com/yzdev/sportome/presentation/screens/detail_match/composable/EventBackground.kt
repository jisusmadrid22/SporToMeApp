package com.yzdev.sportome.presentation.screens.detail_match.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EventBackground(

) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            onDraw = {
                drawLine(
                    color = Color.Black.copy(alpha = 0.25f),
                    start = Offset(x = size.width / 2, y = 0f),
                    end = Offset(x = size.width / 2, y = size.height)
                )
            }
        )
    }
}