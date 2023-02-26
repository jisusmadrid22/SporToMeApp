package com.yzdev.sportome.presentation.screens.team

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yzdev.sportome.domain.model.LocalCompetition
import com.yzdev.sportome.presentation.screens.team.composables.HeaderTeam
import com.yzdev.sportome.presentation.screens.team.composables.SelectorTeamInfo
import com.yzdev.sportome.presentation.screens.team.composables.StatsTeam
import com.yzdev.sportome.presentation.screens.team.composables.TeamResume
import com.yzdev.sportome.presentation.screens.tutorial.CompetitionState
import com.yzdev.sportome.presentation.ui.theme.grayBackground
import kotlinx.coroutines.launch

@Composable
fun TeamInfoScreen(
    teamInfoViewModel: TeamInfoViewModel,
    teamId: Int
) {

    LaunchedEffect(key1 = true, block = {
        teamInfoViewModel.initRequest(teamId)
    })

    TeamInfoLayout(
        teamInfoViewModel = teamInfoViewModel,
        teamId = teamId
    )
}

@Composable
private fun TeamInfoLayout(
    teamInfoViewModel: TeamInfoViewModel,
    teamId: Int
) {
    var numberSelector by remember {
        mutableStateOf(1)
    }

    val teamInfo = teamInfoViewModel.stateTeamInfo.value
    val stateLeague = teamInfoViewModel.stateLeague.value
    val seasonTeam = teamInfoViewModel.stateSeasonTeam.value
    val teamSquad = teamInfoViewModel.stateTeamSquad.value
    val teamStats = teamInfoViewModel.stateTeamStats.value

    var seasonSelected: Int? by remember {
        mutableStateOf(null)
    }

    var selectedLeague by remember {
        mutableStateOf((stateLeague.info?.first()))
    }

    val scope = rememberCoroutineScope()

    Scaffold(modifier = Modifier.fillMaxSize(), backgroundColor = grayBackground) {
        LaunchedEffect(key1 = true, block = {
            Log.e("padd", it.toString())
        })

        Column(modifier = Modifier.fillMaxSize()) {
            HeaderTeam(
                seasonTeam = seasonTeam,
                teamInfo = teamInfo,
                onChangeSeason = {
                    seasonSelected = it
                    scope.launch {
                        teamInfo.info?.response?.first()?.team?.id?.let { it1 ->
                            teamInfoViewModel.getLeagueByTeam(
                                teamId = it1,
                                season = it
                            )
                        }
                    }
                },
                favoriteChange = {

                }
            ) {
                seasonSelected = it
            }

            SelectorTeamInfo(
                numberSelector = numberSelector,
                onClick = {
                    numberSelector = it
                }
            )

            Spacer(modifier = Modifier.padding(bottom = 4.dp))

            AnimationSelector(
                numberSelector = numberSelector,
                teamInfoState = teamInfo,
                stateLeague = stateLeague,
                seasonTeamState = seasonTeam,
                teamSquadState = teamSquad,
                teamStatState = teamStats,
                selectedLeague = selectedLeague,
                selectedLeagueOnChane = {
                    selectedLeague = it
                }
            ){
                scope.launch {
                    seasonSelected?.let {
                        selectedLeague?.let {it1->
                            teamInfoViewModel.getTeamStats(
                                leagueId = it1.idApi,
                                teamId = teamId,
                                season = it
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimationSelector(
    numberSelector: Int,
    teamInfoState: TeamInfoState,
    stateLeague: CompetitionState,
    seasonTeamState: SeasonTeamState,
    teamSquadState: TeamSquadState,
    teamStatState: TeamStatState,
    selectedLeague: LocalCompetition?,
    selectedLeagueOnChane: (LocalCompetition)-> Unit,
    leagueOnChange: (LocalCompetition)-> Unit
) {
    AnimatedContent(
        targetState = numberSelector,
        transitionSpec = {
            if (targetState > initialState) {
                slideInHorizontally { width -> width } + fadeIn() with
                        slideOutHorizontally { width -> -width } + fadeOut()
            } else {
                slideInHorizontally { height -> -height } + fadeIn() with
                        slideOutHorizontally { height -> height } + fadeOut()
            }.using(
                SizeTransform(clip = false)
            )
        }
    ) {targetCount->
        when (targetCount){
            1->{
                TeamResume(
                    teamInfoState = teamInfoState,
                    teamSquadState = teamSquadState
                )
            }
            2->{
                StatsTeam(
                    teamStatState = teamStatState,
                    competitionState = stateLeague,
                    leagueOnChange = {
                        leagueOnChange(it)
                    },
                    selectedLeague = selectedLeague,
                    selectedLeagueOnChane = {
                        selectedLeagueOnChane(it)
                    }
                )
            }
        }
    }
}