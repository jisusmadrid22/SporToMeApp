package com.yzdev.sportome.presentation.screens.tutorial.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.R
import com.yzdev.sportome.presentation.ui.theme.QuickSandFont
import com.yzdev.sportome.presentation.ui.theme.gray

@Composable
fun TopHeaderTutorialOne(

) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 24.dp, start = 24.dp, top = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Hola, Bienvenido a",//AppResource.getString(R.string.welcomeHeader),
                style = TextStyle(
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp,
                    fontFamily = QuickSandFont
                )
            )

            Text(
                text = "* App name *",//AppResource.getString(R.string.app_name),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    fontFamily = QuickSandFont
                )
            )
        }
        
        Box(modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .background(gray)
        )
    }
}

/*
@Preview(showSystemUi = true)
@Composable
fun PreviewHeader() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopHeaderTutorialOne()
    }
}*/
