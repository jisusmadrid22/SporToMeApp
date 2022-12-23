package com.yzdev.sportome.presentation.screens.tutorial.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.presentation.ui.theme.QuickSandFont
import com.yzdev.sportome.presentation.ui.theme.gray

@Composable
fun ContentCenterTutorialOne(
    text: String = AppResource.getString(R.string.tutotialText),
    alpha: Float
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .graphicsLayer(alpha = alpha),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                fontFamily = QuickSandFont
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.padding(top = 24.dp))

        Box(modifier = Modifier
            .size(128.dp)
            .clip(CircleShape)
            .background(gray)
        )
    }
}

/*
@Preview(showSystemUi = true)
@Composable
fun PreviewContent(
    
) {
    ContentCenterTutorialOne(text = "Para comenzar, dinos cual es tu deporte y equipo favorito")
}*/
