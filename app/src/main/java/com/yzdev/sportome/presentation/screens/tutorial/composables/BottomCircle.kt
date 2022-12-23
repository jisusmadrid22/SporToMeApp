package com.yzdev.sportome.presentation.screens.tutorial.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomCircleTutorialOne(

) {
    val colorPrimary = MaterialTheme.colors.primary
    Canvas(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        onDraw = {
            /*drawCircle(
                color = colorPrimary,
                center = Offset(x = (size.width / 2), y = (size.height)),
                radius = size.width * 0.65f
            )*/
            drawOval(
                color = colorPrimary,
                size = Size(width = (size.width * 2), height = (size.height * 4f)),
                topLeft = Offset(x = ((size.width / 2) * -1), y = 0f)
            )
        }
    )
}

/*
@Preview(showSystemUi = true)
@Composable
fun PreviewCanvas(

) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(LocalConfiguration.current.screenHeightDp.dp * 0.275f)) {  //valor fijo muy cercano al diseño -> 212dp
            BottomCircleTutorialOne()
        }
    }
}*/
