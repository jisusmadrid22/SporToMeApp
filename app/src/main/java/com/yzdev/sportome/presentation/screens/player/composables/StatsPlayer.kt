@file:OptIn(ExperimentalMaterialApi::class)

package com.yzdev.sportome.presentation.screens.player.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.composable.canvasUtils.AnimatedShimmerTwoLines
import com.yzdev.sportome.domain.model.InfoPlayerResponse
import com.yzdev.sportome.presentation.screens.player.PlayerResumeState
import com.yzdev.sportome.presentation.ui.theme.RobotoCondensed
import com.yzdev.sportome.presentation.ui.theme.blackLight

@Composable
fun StatsPlayer(
    playerInfo: PlayerResumeState
) {
    when{
        playerInfo.isLoading ->{
            LoadingStatPlayer()
        }
        playerInfo.error.isNotEmpty() ->{
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
                            text = playerInfo.error,
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
            if (playerInfo.info != null){
                StatsPlayerLayout(playerInfo = playerInfo)
            }else{
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
                                text = stringResource(id = R.string.statsPlayerError),
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
        }
    }
}

@Composable
private fun StatsPlayerLayout(
    playerInfo: PlayerResumeState
) {

    var selectedLeague by remember {
        mutableStateOf((playerInfo.info?.response?.first()?.statistics ?: emptyList()).first().league)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            Column(modifier = Modifier.fillMaxWidth()) {

                SelectorLeague(
                    listLeague = playerInfo.info?.response?.first()?.statistics ?: emptyList(),
                    selectedLeague = selectedLeague,
                    selectedLeagueOnChange = {
                        selectedLeague = it
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))
            }

            Column(modifier = Modifier.fillMaxWidth()) {

                InfoPlayerByLeague(
                    infoLeague = playerInfo.info?.response?.first()?.statistics?.find { it.league.id == selectedLeague.id }
                )

                Spacer(modifier = Modifier.height(12.dp))
            }

            for (i in 0..9 step 2){
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ItemStatPlayer(
                        index = i,
                        title = getTitleByIndex(i),
                        content = getListContentByIndex(index = i, stat = playerInfo.info?.response?.first()?.statistics?.find { it.league.id == selectedLeague.id })
                    )

                    ItemStatPlayer(
                        index = i + 1,
                        title = getTitleByIndex(i + 1),
                        content = getListContentByIndex(index = i + 1, stat = playerInfo.info?.response?.first()?.statistics?.find { it.league.id == selectedLeague.id })
                    )
                }
            }
        }
    )
}

private fun getTitleByIndex(index: Int): String{
    return when(index){
        0->{
            //substitute
            AppResource.getString(R.string.subsTitlePlayer)
        }
        1->{
            //shoots
            AppResource.getString(R.string.shootsTitle)
        }
        2->{
            //goals
            AppResource.getString(R.string.goalsTitle)
        }
        3->{
            //passes
            AppResource.getString(R.string.passesTitle)
        }
        4->{
            //tackles
            AppResource.getString(R.string.tacklesTitle)
        }
        5->{
            //duels
            AppResource.getString(R.string.duelsTitle)
        }
        6->{
            //dribbles
            AppResource.getString(R.string.dribblesTitle)
        }
        7->{
            //fouls
            AppResource.getString(R.string.foulsTitle)
        }
        8->{
            //cards
            AppResource.getString(R.string.cardsTitle)
        }
        9->{
            //penalty
            AppResource.getString(R.string.penaltyTitle)
        }
        else->{
            AppResource.getString(R.string.statsTitle)
        }
    }
}

private fun getListContentByIndex(index: Int, stat: InfoPlayerResponse.Response.Statistic?): List<Pair<String, String>>{
    return when(index){
        0->{
            //substitute
            listOf(
                Pair(
                    AppResource.getString(R.string.inTitle),
                    (stat?.substitutes?.inTeam ?: -1).toString()
                ),
                Pair(
                    AppResource.getString(R.string.outTitle),
                    (stat?.substitutes?.outTeam ?: -1).toString()
                ),
                Pair(
                    AppResource.getString(R.string.benchTitle),
                    (stat?.substitutes?.bench ?: -1).toString()
                )
            )
        }
        1->{
            //shoots
            listOf(
                Pair(
                    AppResource.getString(R.string.totalTitle),
                    (stat?.shots?.total ?: -1).toString()
                ),
                Pair(
                    AppResource.getString(R.string.onTitle),
                    (stat?.shots?.on ?: -1).toString()
                )
            )
        }
        2->{
            //goals
            listOf(
                Pair(
                    AppResource.getString(R.string.totalTitle),
                    (stat?.goals?.total ?: -1).toString()
                ),
                Pair(
                    AppResource.getString(R.string.concededTitle),
                    (stat?.goals?.conceded ?: -1).toString()
                ),
                Pair(
                    AppResource.getString(R.string.assistTitle),
                    (stat?.goals?.assists ?: -1).toString()
                ),
                Pair(
                    AppResource.getString(R.string.savesGoalTitle),
                    (stat?.goals?.saves ?: -1).toString()
                )
            )
        }
        3->{
            //passes
            listOf(
                Pair(
                    AppResource.getString(R.string.totalTitle),
                    (stat?.passes?.total ?: -1).toString()
                ),
                Pair(
                    AppResource.getString(R.string.accuaryTitle),
                    (stat?.passes?.accuracy ?: -1).toString()
                )
            )
        }
        4->{
            //tackles
            listOf(
                Pair(
                    AppResource.getString(R.string.totalTitle),
                    (stat?.tackles?.total ?: -1).toString()
                ),
                Pair(
                    AppResource.getString(R.string.blockTitle),
                    (stat?.tackles?.blocks ?: -1).toString()
                ),
                Pair(
                    AppResource.getString(R.string.interceptionsTitle),
                    (stat?.tackles?.interceptions ?: -1).toString()
                )
            )
        }
        5->{
            //duels
            listOf(
                Pair(
                    AppResource.getString(R.string.totalTitle),
                    (stat?.duels?.total ?: -1).toString()
                ),
                Pair(
                    AppResource.getString(R.string.wonTitle),
                    (stat?.duels?.won ?: -1).toString()
                )
            )
        }
        6->{
            //dribbles
            listOf(
                Pair(
                    AppResource.getString(R.string.attempsTitle),
                    (stat?.dribbles?.attempts ?: -1).toString()
                ),
                Pair(
                    AppResource.getString(R.string.successTitle),
                    (stat?.dribbles?.attempts ?: -1).toString()
                ),
                Pair(
                    AppResource.getString(R.string.pastTitle),
                    (stat?.dribbles?.past ?: -1).toString()
                )
            )
        }
        7->{
            //fouls
            listOf(
                Pair(
                    AppResource.getString(R.string.receiverTitle),
                    (stat?.fouls?.drawn ?: -1).toString()
                ),
                Pair(
                    AppResource.getString(R.string.commitedTitle),
                    (stat?.fouls?.committed ?: -1).toString()
                )
            )
        }
        8->{
            //cards
            listOf(
                Pair(
                    AppResource.getString(R.string.yellowTitle),
                    (stat?.cards?.yellow ?: -1).toString()
                ),
                Pair(
                    AppResource.getString(R.string.yellowRedTitle),
                    (stat?.cards?.yellowred ?: -1).toString()
                ),
                Pair(
                    AppResource.getString(R.string.redtitle),
                    (stat?.cards?.red ?: -1).toString()
                )
            )
        }
        9->{
            //penalty
            listOf(
                Pair(
                    AppResource.getString(R.string.wonTitle),
                    (stat?.penalty?.won ?: -1).toString()
                ),
                Pair(
                    AppResource.getString(R.string.commitedTitle),
                    (stat?.penalty?.commited ?: -1).toString()
                ),
                Pair(
                    AppResource.getString(R.string.scoredTitle),
                    (stat?.penalty?.scored ?: -1).toString()
                ),
                Pair(
                    AppResource.getString(R.string.missedTitle),
                    (stat?.penalty?.missed ?: -1).toString()
                ),
                Pair(
                    AppResource.getString(R.string.savedTitle),
                    (stat?.penalty?.saved ?: -1).toString()
                )
            )
        }
        else ->{
            emptyList()
        }
    }
}

@Composable
private fun LoadingStatPlayer(

) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.SpaceBetween,
        columns = GridCells.Fixed(count = 2),
        content = {
            item(
                span = {
                    GridItemSpan(2)
                }
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(12.dp),
                        backgroundColor = Color.White,
                        elevation = 0.dp,
                    ) {
                        AnimatedShimmerTwoLines()
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            item(
                span = {
                    GridItemSpan(2)
                }
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(LocalConfiguration.current.screenWidthDp.dp * 0.5f),
                        shape = RoundedCornerShape(12.dp),
                        backgroundColor = Color.White,
                        elevation = 0.dp,
                    ) {
                        AnimatedShimmerTwoLines()
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            itemsIndexed(List<String?>(6){null}){ i, stat->
                val modifierCustom = if ((i % 2) == 0){
                    Modifier
                        .fillMaxWidth()
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
                    AnimatedShimmerTwoLines()
                }
            }
        }
    )
}