package com.yzdev.sportome.presentation.screens.team

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.Resource
import com.yzdev.sportome.domain.use_case.GetTeamSquad.GetTeamSquadUseCase
import com.yzdev.sportome.domain.use_case.getLeagueByTeam.GetLeagueByTeamUseCase
import com.yzdev.sportome.domain.use_case.getSeasonTeam.GetSeasonTeamUseCase
import com.yzdev.sportome.domain.use_case.getTeamInfo.GetTeamInfoUseCase
import com.yzdev.sportome.domain.use_case.getTeamStats.GetTeamStatsUseCase
import com.yzdev.sportome.presentation.screens.player.CareerPlayerState
import com.yzdev.sportome.presentation.screens.player.PlayerResumeState
import com.yzdev.sportome.presentation.screens.player.SeasonPlayerState
import com.yzdev.sportome.presentation.screens.tutorial.CompetitionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TeamInfoViewModel @Inject constructor(
    private val getSeasonTeamUseCase: GetSeasonTeamUseCase,
    private val getTeamInfoUseCase: GetTeamInfoUseCase,
    private val getTeamSquadUseCase: GetTeamSquadUseCase,
    private val getTeamStatsUseCase: GetTeamStatsUseCase,
    private val getLeagueByTeamUseCase: GetLeagueByTeamUseCase
): ViewModel() {

    private val _stateSeasonTeam = mutableStateOf(SeasonTeamState())
    val stateSeasonTeam: State<SeasonTeamState> = _stateSeasonTeam

    private val _stateTeamInfo = mutableStateOf(TeamInfoState())
    val stateTeamInfo: State<TeamInfoState> = _stateTeamInfo

    private val _stateTeamSquad = mutableStateOf(TeamSquadState())
    val stateTeamSquad: State<TeamSquadState> = _stateTeamSquad

    private val _stateTeamStats = mutableStateOf(TeamStatState())
    val stateTeamStats: State<TeamStatState> = _stateTeamStats

    private val _stateLeague = mutableStateOf(CompetitionState())
    val stateLeague: State<CompetitionState> = _stateLeague

    suspend fun initRequest(teamId: Int){
        getTeamSeason(teamId = teamId)
    }

    private suspend fun getTeamSeason(teamId: Int){
        getSeasonTeamUseCase(teamId = teamId).onEach { result->
            when(result){
                is Resource.Error -> {
                    _stateSeasonTeam.value = SeasonTeamState(error = result.message ?: AppResource.getString(
                        R.string.erroGeneric))
                }
                is Resource.Loading -> {
                    _stateSeasonTeam.value = SeasonTeamState(isLoading = true)
                }
                is Resource.Success -> {
                    _stateSeasonTeam.value = SeasonTeamState(info = result.data)

                    getTeamInfo(teamId)

                    getTeamSquad(teamId)

                    stateSeasonTeam.value.info?.let {
                        getLeagueByTeam(teamId = teamId, season = it.listSeason.last())
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private suspend fun getTeamInfo(teamId: Int){
        getTeamInfoUseCase(idTeam = teamId).onEach { result->
            when(result){
                is Resource.Error -> {
                    _stateTeamInfo.value = TeamInfoState(error = result.message ?: AppResource.getString(
                        R.string.erroGeneric))
                }
                is Resource.Loading -> {
                    _stateTeamInfo.value = TeamInfoState(isLoading = true)
                }
                is Resource.Success -> {
                    _stateTeamInfo.value = TeamInfoState(info = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    private suspend fun getTeamSquad(teamId: Int){
        getTeamSquadUseCase(teamId = teamId).onEach { result->
            when(result){
                is Resource.Error -> {
                    _stateTeamSquad.value = TeamSquadState(error = result.message ?: AppResource.getString(
                        R.string.erroGeneric))
                }
                is Resource.Loading -> {
                    _stateTeamSquad.value = TeamSquadState(isLoading = true)
                }
                is Resource.Success -> {
                    _stateTeamSquad.value = TeamSquadState(info = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    /** every request*/
    suspend fun getTeamStats(
        leagueId: Int,
        teamId: Int,
        season: Int
    ){
        getTeamStatsUseCase(leagueId = leagueId, teamId = teamId, season = season).onEach { result->
            when(result){
                is Resource.Error -> {
                    _stateTeamStats.value = TeamStatState(error = result.message ?: AppResource.getString(
                        R.string.erroGeneric))
                }
                is Resource.Loading -> {
                    _stateTeamStats.value = TeamStatState(isLoading = true)
                }
                is Resource.Success -> {
                    _stateTeamStats.value = TeamStatState(info = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun getLeagueByTeam(
        teamId: Int,
        season: Int
    ){
        getLeagueByTeamUseCase(idTeam = teamId, season = season).onEach { result->
            when(result){
                is Resource.Error -> {
                    _stateLeague.value = CompetitionState(error = result.message ?: AppResource.getString(
                        R.string.erroGeneric))
                }
                is Resource.Loading -> {
                    _stateLeague.value = CompetitionState(isLoading = true)
                }
                is Resource.Success -> {
                    _stateLeague.value = CompetitionState(info = result.data)

                    stateLeague.value.info?.let {
                        getTeamStats(
                            leagueId = it.first().idApi,
                            teamId = teamId,
                            season = season
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

}