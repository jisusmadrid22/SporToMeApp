package com.yzdev.sportome.common.composable.canvasUtils

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedShimmerTwoLines() {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    ShimmerGridItemTwoLines(brush = brush)
}

@Composable
private fun ShimmerGridItemTwoLines(brush: Brush) {
    Column(
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(
            modifier = Modifier
                .height(20.dp)
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth(fraction = 0.7f)
                .background(brush)
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Spacer(
            modifier = Modifier
                .height(20.dp)
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth(fraction = 0.9f)
                .background(brush)
        )
    }
}