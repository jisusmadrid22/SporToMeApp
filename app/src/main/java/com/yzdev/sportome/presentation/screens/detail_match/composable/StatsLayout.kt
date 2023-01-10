package com.yzdev.sportome.presentation.screens.detail_match.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.presentation.ui.theme.QuickSandFont
import com.yzdev.sportome.presentation.ui.theme.gray

@Composable
fun StatsLayout(
    
) {
    Column {
        LinearProgressStat(valueParent = 30f, valueHome = 15f)
        LinearProgressStat(valueParent = 3f, valueHome = 1f)
        LinearProgressStat(valueParent = 5f, valueHome = 2f)
        LinearProgressStat(valueParent = 16f, valueHome = 8f)
        LinearProgressStat(valueParent = 1f, valueHome = 0f)
    }
}

@Composable
fun LinearProgressStat(
    valueParent: Float,
    valueHome: Float
) {

    val percentHome = ((valueHome * 100) / valueParent) / 100
    val percentAway = 1 - percentHome

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 2.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Name stat",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                fontFamily = QuickSandFont,
                color = Color.Black
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(2.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .clip(CircleShape)
                .background(gray)
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.Center
            ) {

                Box(modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f),
                    contentAlignment = Alignment.CenterEnd
                ){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(percentHome)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(topStart = 128.dp, bottomStart = 128.dp))
                            .background(MaterialTheme.colors.primary)
                    )
                }

                Box(modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(percentAway)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(bottomEnd = 128.dp, topEnd = 128.dp))
                            .background(MaterialTheme.colors.primary)
                    )
                }

            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Box(modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
                    .background(Color.White))
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = valueHome.toInt().toString(),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    fontFamily = QuickSandFont,
                    color = Color.Black.copy(alpha = 0.25f)
                ),
                textAlign = TextAlign.Start
            )

            Text(
                text = (valueParent - valueHome).toInt().toString(),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    fontFamily = QuickSandFont,
                    color = Color.Black.copy(alpha = 0.25f)
                ),
                textAlign = TextAlign.End
            )
        }

    }
}