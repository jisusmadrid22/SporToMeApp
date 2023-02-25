@file:OptIn(ExperimentalMaterialApi::class)

package com.yzdev.sportome.presentation.screens.player.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.common.AutoResizedText
import com.yzdev.sportome.presentation.ui.theme.RobotoCondensed
import com.yzdev.sportome.presentation.ui.theme.gray

@Composable
fun ItemStatPlayer(
    index: Int,
    title: String,
    content: List<Pair<String, String>>
) {
    val modifierCustom = if ((index % 2) == 0){
        Modifier
            .fillMaxWidth(0.5f)
            .heightIn(min = LocalConfiguration.current.screenWidthDp.dp * 0.25f)
            .padding(end = 12.dp, bottom = 10.dp)
    }else{
        Modifier
            .fillMaxWidth()
            .heightIn(min = LocalConfiguration.current.screenWidthDp.dp * 0.25f)
            .padding(start = 12.dp, bottom = 10.dp)
    }

    Card(
        modifier = modifierCustom,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(24.dp),
        elevation = 0.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
            ) {
                AutoResizedText(
                    text = title,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        fontFamily = RobotoCondensed,
                        color = Color.Black
                    )
                )
            }

            content.forEach {
                if (it.second != "-1"){
                    AutoResizedText(
                        text = "${it.first} ${it.second}",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 8.sp,
                            fontFamily = RobotoCondensed,
                            color = Color.Black.copy(alpha = 0.5f)
                        )
                    )
                }
            }
        }
    }
}