package com.yzdev.sportome.presentation.screens.on_boarding.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.yzdev.sportome.presentation.ui.theme.blackLight
import com.yzdev.sportome.presentation.ui.theme.blueThunder
import com.yzdev.sportome.presentation.ui.theme.greenSuccess
import com.yzdev.sportome.presentation.ui.theme.redMain

@Composable
fun DiamondButton(
    sizeButton: Dp,
    numberStep: Int,
    onClick: (Int) -> Unit
) {
    Box(modifier = Modifier.size(sizeButton), contentAlignment = Alignment.Center) {
        Box(modifier = Modifier
            .fillMaxSize()
        ) {
            DiamondStepLine(
                numberStep = numberStep,
                sizeButton = sizeButton
            )
        }

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
            contentAlignment = Alignment.Center
        ){
            DiamondButtonBackground(){
                onClick(numberStep)
            }

            Icon(
                modifier = Modifier
                    .fillMaxSize(0.5f)
                    .padding(4.dp),
                imageVector = if (numberStep < 4) Icons.Rounded.KeyboardArrowRight else Icons.Rounded.Check,
                contentDescription = "",
                tint = Color.White
            )
        }
    }
}

@Composable
private fun DiamondStepLine(
    numberStep: Int,
    sizeButton: Dp
) {
    val lineWidth = 4f
    val delayAnimation = 1000
    val color = MaterialTheme.colors.secondary

    //animations
    val stepOne = animateOffsetAsState(
        targetValue = if (numberStep >= 1) Offset(x = LocalDensity.current.run { sizeButton.toPx() }, y = LocalDensity.current.run { sizeButton.toPx() } / 2) else Offset(x = LocalDensity.current.run { sizeButton.toPx() } / 2, y = 0f),
        animationSpec = tween(delayAnimation)
    )

    val stepTwo = animateOffsetAsState(
        targetValue = if (numberStep >= 2) Offset(x = LocalDensity.current.run { sizeButton.toPx() } / 2, y = LocalDensity.current.run { sizeButton.toPx() }) else Offset(x = LocalDensity.current.run { sizeButton.toPx() }, y = LocalDensity.current.run { sizeButton.toPx() } / 2),
        animationSpec = tween(delayAnimation)
    )

    val stepThree = animateOffsetAsState(
        targetValue = if (numberStep >= 3) Offset(x = 0f, y = LocalDensity.current.run { sizeButton.toPx() } / 2) else Offset(x = LocalDensity.current.run { sizeButton.toPx() } / 2, y = LocalDensity.current.run { sizeButton.toPx() }),
        animationSpec = tween(delayAnimation)
    )

    val stepFour = animateOffsetAsState(
        targetValue = if (numberStep >= 4) Offset(x = LocalDensity.current.run { sizeButton.toPx() } / 2, y = 0f) else Offset(x = 0f, y = LocalDensity.current.run { sizeButton.toPx() } / 2),
        animationSpec = tween(delayAnimation)
    )

    Canvas(
        modifier = Modifier.fillMaxSize(),
        onDraw = {
            //step 1
            drawLine(
                color = color,
                start = Offset(x = size.width / 2, y = 0f),
                end = stepOne.value,
                strokeWidth = lineWidth
            )

            //step 2
            drawLine(
                color = color,
                start = Offset(x = size.width, y = size.height / 2),
                end = stepTwo.value,
                strokeWidth = lineWidth
            )

            //step 3
            drawLine(
                color = color,
                start = Offset(x = size.width / 2, y = size.height),
                end = stepThree.value,
                strokeWidth = lineWidth
            )

            //step 4
            drawLine(
                color = color,
                start = Offset(x = 0f, y = size.height / 2),
                end = stepFour.value,
                strokeWidth = lineWidth
            )
        }
    )
}

@Composable
private fun DiamondButtonBackground(
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Canvas(
        modifier = Modifier.fillMaxSize().clickable(indication = null, interactionSource = interactionSource){onClick()},
        onDraw = {
            rotate(degrees = 45F) {
                drawRect(
                    color = blackLight,
                    topLeft = Offset(x = size.width / 6F, y = size.height / 6F),
                    size = size / 1.5f
                )
            }

        }
    )
}
