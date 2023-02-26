@file:OptIn(ExperimentalMaterialApi::class)

package com.yzdev.sportome.presentation.screens.team.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.common.AutoResizedText
import com.yzdev.sportome.common.composable.canvasUtils.AnimatedShimmerTwoLines
import com.yzdev.sportome.domain.model.TeamStatsLocalResponse
import com.yzdev.sportome.presentation.ui.theme.RobotoCondensed
import com.yzdev.sportome.R
import com.yzdev.sportome.presentation.ui.theme.blackLight

@Composable
fun StatsMatchesForTeam(
    statsFixture: TeamStatsLocalResponse?
) {
    if (statsFixture != null){
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            backgroundColor = Color.White,
            elevation = 0.dp
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.weight(1f)) {
                    FixtureStat(nameStat = stringResource(
                        id = R.string.winTitle),
                        valueStat = (statsFixture.response.fixtures.wins.total ?: 0).toString(),
                        1
                    )
                }

                Box(modifier = Modifier.weight(1f)) {
                    FixtureStat(nameStat = stringResource(
                        id = R.string.drawTitle),
                        valueStat = (statsFixture.response.fixtures.draws.total ?: 0).toString(),
                        2
                    )
                }

                Box(modifier = Modifier.weight(1f)) {
                    FixtureStat(nameStat = stringResource(
                        id = R.string.loseTitle),
                        valueStat = (statsFixture.response.fixtures.loses.total ?: 0).toString(),
                        3
                    )
                }
            }
        }
    } else {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            backgroundColor = Color.White,
            elevation = 0.dp
        ) {
            Column(
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.errorFixtureTeam),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        fontFamily = RobotoCondensed,
                        color = blackLight
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun FixtureStat(
    nameStat: String,
    valueStat: String,
    index: Int
) {

    val modifierCustom = when (index){
        1-> {
            Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = MaterialTheme.colors.primary, shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp))
                .clip(RoundedCornerShape(bottomStart = 12.dp, topStart = 12.dp))
        }
        2 ->{
            Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = MaterialTheme.colors.primary)
        }
        3 ->{
            Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = MaterialTheme.colors.primary, shape = RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp))
                .clip(RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp))
        }
        else -> {
            Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = MaterialTheme.colors.primary)
        }
    }

    Box(
        modifier = modifierCustom
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
            Box(modifier = Modifier.fillMaxWidth()) {
                AutoResizedText(
                    text = nameStat,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        fontFamily = RobotoCondensed,
                        color = Color.Black.copy(alpha = 0.5f)
                    )
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd) {
                AutoResizedText(
                    text = valueStat,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        fontFamily = RobotoCondensed,
                        color = Color.Black
                    )
                )
            }
        }
    }
}