package com.yzdev.sportome.presentation.screens.today_match.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.yzdev.sportome.common.getNameDayWeek
import com.yzdev.sportome.common.unixToDayWeek
import com.yzdev.sportome.domain.model.LocalMatch
import com.yzdev.sportome.presentation.screens.today_match.MatchesWeekState

@Composable
fun CalendarWeek(
    days: List<Int>,
    currentDay: Int,
    listWeekMatch: MatchesWeekState
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
                        isSelected = i == currentDay,
                        isTodayMatch = getDayMatch(listMatch = listWeekMatch.info ?: emptyList(), day = i) == i
                    )
                }
            }

            Spacer(modifier = Modifier.padding(top = 4.dp))
        }
    }
}

private fun getDayMatch(listMatch: List<LocalMatch>, day: Int): Int? {
    listMatch.forEach {
        if (it.matchDay == day){
            return day
        }
    }

    return null
}