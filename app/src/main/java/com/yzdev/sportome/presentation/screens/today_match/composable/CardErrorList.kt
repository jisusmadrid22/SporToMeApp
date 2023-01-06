package com.yzdev.sportome.presentation.screens.today_match.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.common.composable.canvasUtils.BorderPath
import com.yzdev.sportome.presentation.ui.theme.QuickSandFont

@Composable
fun CardErrorList(
    message: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalConfiguration.current.screenWidthDp.dp * 0.35f)
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        BorderPath(
            colorLine = Color.Black,
            radius = CornerRadius(16f, 16f)
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = message,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                fontFamily = QuickSandFont
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CardErrorListSmall(
    message: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalConfiguration.current.screenWidthDp.dp * 0.15f)
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        BorderPath(
            colorLine = Color.Black,
            radius = CornerRadius(16f, 16f),
            lineSize = 1f
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = message,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp,
                fontFamily = QuickSandFont
            ),
            textAlign = TextAlign.Center
        )
    }
}

