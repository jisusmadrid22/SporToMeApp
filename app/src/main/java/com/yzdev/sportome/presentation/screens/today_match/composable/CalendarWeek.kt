package com.yzdev.sportome.presentation.screens.today_match.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.yzdev.sportome.common.getNameDayWeek

@Composable
fun CalendarWeek(
    days: List<Int>,
    currentDay: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White), contentAlignment = Alignment.Center
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                days.forEachIndexed { index, i ->
                    DayWeekDesign(
                        nameDay = getNameDayWeek(index).take(3),
                        dayNumber = i,
                        isSelected = i == currentDay
                    )
                }
            }

            Spacer(modifier = Modifier.padding(top = 4.dp))
        }
    }
}