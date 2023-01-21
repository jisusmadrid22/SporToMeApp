package com.yzdev.sportome.presentation.screens.player.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
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
fun HeaderPlayer(

) {
    val height = LocalConfiguration.current.screenHeightDp.dp
    val width = LocalConfiguration.current.screenWidthDp.dp

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(height * 0.3f)
            .padding(8.dp),
        backgroundColor = MaterialTheme.colors.primary,
        shape = RoundedCornerShape(12.dp),
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(gray)
            )

            Text(
                text = "Neymar\nda silva santos Junior",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    fontFamily = QuickSandFont,
                    color = Color.White
                ),
                textAlign = TextAlign.Center
            )

            Text(
                text = "Brasil",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    fontFamily = QuickSandFont,
                    color = Color.White.copy(alpha = 0.5f)
                ),
                textAlign = TextAlign.Center
            )

            Text(
                text = "Delantero",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    fontFamily = QuickSandFont,
                    color = Color.White.copy(alpha = 0.5f)
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}