@file:OptIn(ExperimentalMaterialApi::class)

package com.yzdev.sportome.presentation.screens.team.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.R
import com.yzdev.sportome.common.composable.canvasUtils.AnimatedShimmerTwoLines
import com.yzdev.sportome.common.getFirstAndLastName
import com.yzdev.sportome.domain.model.TeamSquadLocalResponse
import com.yzdev.sportome.presentation.screens.team.TeamInfoState
import com.yzdev.sportome.presentation.screens.team.TeamSquadState
import com.yzdev.sportome.presentation.ui.theme.RobotoCondensed
import com.yzdev.sportome.presentation.ui.theme.blackLight
import com.yzdev.sportome.presentation.ui.theme.gray

@Composable
fun TeamResume(
    teamInfoState: TeamInfoState,
    teamSquadState: TeamSquadState
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        item {
            when{
                teamInfoState.isLoading -> {
                    ResumeTeamVenue(teamInfo = null)
                }
                teamInfoState.error.isNotEmpty() ->{
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = teamInfoState.error,
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp,
                                        fontFamily = RobotoCondensed,
                                        color = blackLight
                                    ),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
                else ->{
                    ResumeTeamVenue(teamInfo = teamInfoState.info)
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                /*Text(
                    text = stringResource(R.string.managerTitle),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        fontFamily = RobotoCondensed,
                        color = Color.Black
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                ManagerCard(managerName = "", managerPhoto = "")*/

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(R.string.lineupTitle),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        fontFamily = RobotoCondensed,
                        color = Color.Black
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        item {
            when{
                teamSquadState.isLoading ->{
                    TeamLineup(teamSquad = null)
                }
                teamSquadState.error.isNotEmpty() ->{
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = teamSquadState.error,
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp,
                                        fontFamily = RobotoCondensed,
                                        color = blackLight
                                    ),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
                else ->{
                    TeamLineup(teamSquad = teamSquadState.info)
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
private fun ManagerCard(
    managerName: String,
    managerPhoto: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        elevation = 0.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(12.dp),
        onClick = {

        }
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 6.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(gray)
            )

            Spacer(modifier = Modifier.width(4.dp))

            val names = getFirstAndLastName(managerName)

            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            fontFamily = RobotoCondensed,
                            color = Color.Black.copy(alpha = 0.25f),
                        )
                    ){
                        append("${names.first} ")
                    }
                    if (names.second.isNotEmpty()){
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                fontFamily = RobotoCondensed,
                                color = Color.Black,
                            )
                        ){
                            append(names.second)
                        }
                    }
                },
                textAlign = TextAlign.Start
            )
        }
    }
}

@Composable
private fun TeamLineup(
    teamSquad: TeamSquadLocalResponse?
) {
    if (teamSquad != null){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            elevation = 0.dp,
            shape = RoundedCornerShape(12.dp),
            backgroundColor = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                teamSquad.response.first().players.forEach {
                    LineupPlayerItem(it)
                }
            }
        }
    }else{
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalConfiguration.current.screenWidthDp.dp * 0.75f)
                .padding(horizontal = 24.dp),
            elevation = 0.dp,
            shape = RoundedCornerShape(12.dp),
            backgroundColor = Color.White
        ) {
            Column {
                AnimatedShimmerTwoLines()

                AnimatedShimmerTwoLines()

                AnimatedShimmerTwoLines()
            }
        }
    }
}