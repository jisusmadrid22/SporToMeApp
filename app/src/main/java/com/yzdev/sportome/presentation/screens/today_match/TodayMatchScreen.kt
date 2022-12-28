package com.yzdev.sportome.presentation.screens.today_match

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.composable.topBarDesign.TopBarModern
import com.yzdev.sportome.common.getAllDateOfWeek
import com.yzdev.sportome.common.getCurrentDay
import com.yzdev.sportome.presentation.screens.today_match.composable.CalendarWeek
import kotlinx.coroutines.launch

@Composable
fun TodayMatchScreen(
    scaffoldState: ScaffoldState
) {
    TodayMatchLayout(
        scaffoldState = scaffoldState
    )
}

@Composable
fun TodayMatchLayout(
    scaffoldState: ScaffoldState
) {
    val scope = rememberCoroutineScope()

    LazyColumn{
        item {
            TopBarModern(
                textTitle = AppResource.getString(R.string.todayMatchTitle),
                actionStartButton = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                },
                iconStartButton = Icons.Rounded.Menu
            )
        }

        item {
            CalendarWeek(
                days = getAllDateOfWeek(),
                currentDay = getCurrentDay()
            )
        }
    }
}