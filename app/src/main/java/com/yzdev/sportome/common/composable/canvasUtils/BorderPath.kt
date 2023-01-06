package com.yzdev.sportome.common.composable.canvasUtils

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp

@Composable
fun BorderPath(
    colorLine: Color = MaterialTheme.colors.surface,
    lineSize: Float = 4f,
    radius: CornerRadius = CornerRadius.Zero,
) {
    val stroke = Stroke(
        width = lineSize,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(30f, 30f), 0f)
    )
    Canvas(Modifier.fillMaxSize()) {
        drawRoundRect(
            color = colorLine,
            style = stroke,
            cornerRadius = radius
        )
    }
}