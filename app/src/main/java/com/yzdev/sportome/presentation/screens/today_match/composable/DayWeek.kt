package com.yzdev.sportome.presentation.screens.today_match.composable

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.presentation.ui.theme.QuickSandFont
import com.yzdev.sportome.presentation.ui.theme.greenSuccess

@Composable
fun DayWeekDesign(
    nameDay: String,
    dayNumber: Int,
    isTodayMatch: Boolean,
    isSelected: Boolean
) {

    val colorName = if(isSelected) Color.White.copy(alpha = 0.5f) else Color.Black.copy(alpha = 0.5f)
    val colorDay = if(isSelected) Color.White else Color.Black

    Box(
        contentAlignment = Alignment.TopEnd
    ) {
        if (isTodayMatch){
            Box {
                Box(modifier = Modifier
                    .width(8.dp)
                    .height(8.dp)
                    .clip(CircleShape)
                    .background(greenSuccess)
                )
            }
        }

        Card(
            elevation = 0.dp,
            backgroundColor = if (isSelected) MaterialTheme.colors.primary else Color.Transparent,
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = nameDay,
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 10.sp,
                        fontFamily = QuickSandFont,
                        color = colorName
                    )
                )

                Spacer(modifier = Modifier.padding(top = 4.dp))

                Text(
                    text = dayNumber.toString(),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        fontFamily = QuickSandFont,
                        color = colorDay
                    )
                )
            }
        }
    }
}