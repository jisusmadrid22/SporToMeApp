package com.yzdev.sportome.presentation.screens.today_match

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.composable.topBarDesign.TopBarModern
import com.yzdev.sportome.common.getAllDateOfWeek
import com.yzdev.sportome.common.getCurrentDay
import com.yzdev.sportome.common.getTeamMatch
import com.yzdev.sportome.presentation.screens.today_match.composable.CalendarWeek
import com.yzdev.sportome.presentation.screens.today_match.composable.FavoriteMatchDesign
import com.yzdev.sportome.presentation.ui.theme.QuickSandFont
import com.yzdev.sportome.presentation.ui.theme.gray
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

        item {
            Spacer(modifier = Modifier.padding(top = 12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = AppResource.getString(R.string.match_favorites),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        fontFamily = QuickSandFont
                    )
                )
            }
            Spacer(modifier = Modifier.padding(top = 12.dp))
        }

        item {
            LazyRow{
                item {
                    Spacer(modifier = Modifier.padding(start = 24.dp))
                }

                items(getTeamMatch()){ item->
                    FavoriteMatchDesign(
                        item = item,
                        onClick = {

                        }
                    )
                }

                item {
                    Spacer(modifier = Modifier.padding(end = 24.dp))
                }
            }
        }
    }
}