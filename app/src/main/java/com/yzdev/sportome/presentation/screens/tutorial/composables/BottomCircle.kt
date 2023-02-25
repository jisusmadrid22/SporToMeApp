package com.yzdev.sportome.presentation.screens.tutorial.composables

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomCircleTutorialOne(
    initAnimationCircle: Boolean,
    finishAnimationCircle: ()-> Unit
) {
    val colorPrimary = MaterialTheme.colors.primary

    var finishAnimation by remember {
        mutableStateOf(false)
    }

    val heightTransition = animateDpAsState(
        targetValue = if(!initAnimationCircle) 0.dp else LocalConfiguration.current.screenHeightDp.dp,
        animationSpec = tween(2000),
        finishedListener = {
            finishAnimation = true
            finishAnimationCircle()
        }
    )

    if (!finishAnimation){
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .absoluteOffset(y = -heightTransition.value),
            onDraw = {
                drawOval(
                    color = colorPrimary,
                    size = Size(width = (size.width * 2), height = (size.height * 4f)),
                    topLeft = Offset(x = ((size.width / 2) * -1), y = 0f)
                )

                drawRect(
                    color = colorPrimary,
                    topLeft = Offset(x = 0f, y = (size.height / 2)),
                    size = Size(width = size.width, height = heightTransition.value.value * 2.5f)
                )
            }
        )
    }
}

/*@Preview(showSystemUi = true)
@Composable
fun PreviewCanvas(

) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(LocalConfiguration.current.screenHeightDp.dp * 0.275f)) {  //valor fijo muy cercano al diseño -> 212dp
            BottomCircleTutorialOne(false)
        }
    }
}*/
