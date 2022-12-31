package com.yzdev.sportome.common.composable.topBarDesign

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.presentation.ui.theme.QuickSandFont

@Composable
fun TopBarModern(
    iconStartButton: ImageVector,
    actionStartButton: ()-> Unit,
    textTitle: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                actionStartButton()
            }) {
                Icon(imageVector = iconStartButton, contentDescription = "")

            }

            Text(
                text = textTitle,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    fontFamily = QuickSandFont
                )
            )
        }
    }
}