package com.yzdev.sportome.presentation.screens.tutorial.composables

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ArrowAnimationTutorial(
    
) {
    val animation = rememberInfiniteTransition()

    val alphaOne by animation.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(750, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val alphaTwo by animation.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(750, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Box(
        modifier = Modifier
            .width(24.dp)
            .height(28.dp)
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize(),
            onDraw = {
                /** arrow top*/
                drawLine(
                    color = Color.White,
                    start = Offset(x = 0f, y = (size.height / 2)),
                    end = Offset(x = (size.width / 2), y = 0f),
                    strokeWidth = 6f,
                    alpha = alphaTwo
                )

                drawLine(
                    color = Color.White,
                    start = Offset(x = (size.width / 2) - 3, y = 0f),
                    end = Offset(x = size.width - 3, y = (size.height / 2)),
                    strokeWidth = 6f,
                    alpha = alphaTwo
                )

                /** arrow bottom*/
                drawLine(
                    color = Color.White,
                    start = Offset(x = 0f, y = size.height),
                    end = Offset(x = (size.width / 2), y = (size.height / 2)),
                    strokeWidth = 6f,
                    alpha = alphaOne
                )

                drawLine(
                    color = Color.White,
                    start = Offset(x = (size.width / 2) - 3, y = (size.height / 2)),
                    end = Offset(x = size.width - 3, y = size.height),
                    strokeWidth = 6f,
                    alpha = alphaOne
                )
            }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewAnimation() {
    Box(modifier = Modifier.fillMaxSize()){
        ArrowAnimationTutorial()
    }
}