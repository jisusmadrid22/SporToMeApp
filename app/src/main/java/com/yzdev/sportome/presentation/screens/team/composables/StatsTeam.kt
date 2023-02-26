package com.yzdev.sportome.presentation.screens.team.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
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
import com.yzdev.sportome.common.AutoResizedText
import com.yzdev.sportome.common.composable.canvasUtils.AnimatedShimmerTwoLines
import com.yzdev.sportome.domain.model.InfoPlayerResponse
import com.yzdev.sportome.domain.model.LocalCompetition
import com.yzdev.sportome.domain.model.TeamStatsLocalResponse
import com.yzdev.sportome.presentation.screens.player.composables.*
import com.yzdev.sportome.presentation.screens.team.TeamStatState
import com.yzdev.sportome.presentation.screens.tutorial.CompetitionState
import com.yzdev.sportome.presentation.ui.theme.RobotoCondensed
import com.yzdev.sportome.presentation.ui.theme.blackLight

@Composable
fun StatsTeam(
    teamStatState: TeamStatState,
    competitionState: CompetitionState,
    selectedLeague: LocalCompetition?,
    selectedLeagueOnChane: (LocalCompetition)-> Unit,
    leagueOnChange: (LocalCompetition)-> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            when{
                competitionState.isLoading ->{

                }
                competitionState.error.isNotEmpty() ->{

                }
                else ->{
                    LaunchedEffect(key1 = true, block = {
                        competitionState.info?.let {
                            selectedLeagueOnChane(it.first())
                        }
                    })
                    SelectorLeagueTeam(
                        listLeague = competitionState.info,
                        selectedLeague = selectedLeague,
                        selectedLeagueOnChange = {
                            selectedLeagueOnChane(it)
                            leagueOnChange(it)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            when{
                teamStatState.isLoading -> {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        backgroundColor = Color.White,
                        elevation = 0.dp
                    ) {
                        Column(
                            modifier = Modifier.padding(vertical = 12.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AnimatedShimmerTwoLines()
                        }
                    }
                }
                teamStatState.error.isNotEmpty() ->{
                    StatsMatchesForTeam(
                        statsFixture = null
                    )
                }
                else -> {
                    StatsMatchesForTeam(
                        statsFixture = teamStatState.info
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            when{
                teamStatState.isLoading -> {
                    for (i in 0..3 step 2){
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            ItemStatTeam(
                                index = i,
                                title = getTitleByIndex(i),
                                content = null,
                                isLoading = true
                            )

                            ItemStatTeam(
                                index = i + 1,
                                title = getTitleByIndex(i + 1),
                                content = null,
                                isLoading = true
                            )
                        }
                    }
                }
                teamStatState.error.isNotEmpty() ->{
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
                                .padding(horizontal = 14.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = teamStatState.error,
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
                else ->{
                    if (teamStatState.info != null){
                        for (i in 0..3 step 2){
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                ItemStatTeam(
                                    index = i,
                                    title = getTitleByIndex(i),
                                    content = getListContentByIndex(index = i, stat = teamStatState.info.response)
                                )

                                ItemStatTeam(
                                    index = i + 1,
                                    title = getTitleByIndex(i + 1),
                                    content = getListContentByIndex(index = i + 1, stat = teamStatState.info.response)
                                )
                            }
                        }
                    } else{
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
                                        text = stringResource(id = R.string.errorStatTeam),
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
                }
            }
        }
    )
}

@Composable
fun ItemStatTeam(
    index: Int,
    isLoading: Boolean = false,
    title: String,
    content: List<Pair<String, String>>?
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
        if (!isLoading){
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

                content?.forEach {
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
        }else{
            AnimatedShimmerTwoLines()
        }
    }
}

private fun getTitleByIndex(index: Int): String{
    return when(index){
        0->{
            //goals
            AppResource.getString(R.string.goalsTitle)
        }
        1->{
            //clean_sheet
            AppResource.getString(R.string.matchesClean)
        }
        2->{
            //failedScore
            AppResource.getString(R.string.failedScore)
        }
        3->{
            //penalty
            AppResource.getString(R.string.penaltyTitle)
        }
        else->{
            AppResource.getString(R.string.statsTitle)
        }
    }
}

private fun getListContentByIndex(index: Int, stat: TeamStatsLocalResponse.Response): List<Pair<String, String>>{
    return when(index){
        0->{
            //goals
            listOf(
                Pair(
                    AppResource.getString(R.string.scoredTitle),
                    (stat.goals.forTeam.total.total ?: -1).toString()
                ),
                Pair(
                    AppResource.getString(R.string.receiverGoalsTitle),
                    (stat.goals.against.total.total ?: -1).toString()
                ),
            )
        }
        1->{
            //cleanSheet
            listOf(
                Pair(
                    AppResource.getString(R.string.homeTeamTitle),
                    (stat.clean_sheet.home ?: -1).toString()
                ),
                Pair(
                    AppResource.getString(R.string.awayTitle),
                    (stat.clean_sheet.away ?: -1).toString()
                )
            )
        }
        2->{
            //failedScore
            listOf(
                Pair(
                    AppResource.getString(R.string.homeTeamTitle),
                    (stat.failed_to_score.home ?: -1).toString()
                ),
                Pair(
                    AppResource.getString(R.string.awayTitle),
                    (stat.failed_to_score.away ?: -1).toString()
                )
            )
        }
        3->{
            //passes
            listOf(
                Pair(
                    AppResource.getString(R.string.scoredTitle),
                    (stat.penalty.scored.total ?: -1).toString()
                ),
                Pair(
                    AppResource.getString(R.string.missedTitle),
                    (stat.penalty.missed.total ?: -1).toString()
                )
            )
        }
        else ->{
            emptyList()
        }
    }
}