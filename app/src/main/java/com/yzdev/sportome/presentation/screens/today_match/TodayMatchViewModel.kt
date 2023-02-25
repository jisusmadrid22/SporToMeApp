package com.yzdev.sportome.presentation.screens.today_match

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yzdev.sportome.common.Resource
import com.yzdev.sportome.common.timeToUnix
import com.yzdev.sportome.common.unixToDateTime
import com.yzdev.sportome.domain.use_case.getAllMatchesTodayCompetition.GetAllMatchesTodayUseCase
import com.yzdev.sportome.domain.use_case.getAllMatchesWeek.GetAllMatchesWeekUseCase
import com.yzdev.sportome.domain.use_case.getAllTodayMatchesTeam.GetAllTodayMatchesTeamUseCase
import com.yzdev.sportome.presentation.screens.tutorial.CompetitionState
import com.yzdev.sportome.presentation.screens.tutorial.CountryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

@HiltViewModel
class TodayMatchViewModel @Inject constructor(
    private val getAllMatchesWeekUseCase: GetAllMatchesWeekUseCase,
    private val getAllMatchesTodayUseCase: GetAllMatchesTodayUseCase,
    private val getAllTodayMatchesTeamUseCase: GetAllTodayMatchesTeamUseCase
): ViewModel() {

    private val _stateListMatchesWeek = mutableStateOf(MatchesWeekState())
    val stateListMatchesWeek: State<MatchesWeekState> = _stateListMatchesWeek

    private val _stateListMatchesCompetition = mutableStateOf(MatchesCompetitionState())
    val stateListMatchesCompetition: State<MatchesCompetitionState> = _stateListMatchesCompetition

    private val _stateListMatchesTeam = mutableStateOf(MatchesTeamState())
    val stateListMatchesTeam: State<MatchesTeamState> = _stateListMatchesTeam

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllMatchesWeek()
            getAllMatchesCompetitionToday()
            unixToDateTime(timeToUnix())?.let { getAllMatchesTeamToday(it) }
        }
    }

    suspend fun getAllMatchesWeek(){
        getAllMatchesWeekUseCase().onEach { result->
            when(result){
                is Resource.Error -> {
                    Log.e("week", "error week ${result.message}")
                    _stateListMatchesWeek.value = MatchesWeekState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    Log.e("week", "loading")
                    _stateListMatchesWeek.value = MatchesWeekState(isLoading = true)
                }
                is Resource.Success -> {
                    Log.e("week", "success ${result.data}")
                    _stateListMatchesWeek.value = MatchesWeekState(info = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun getAllMatchesCompetitionToday(){
        getAllMatchesTodayUseCase().onEach { result->
            when(result){
                is Resource.Error -> {
                    Log.e("week", "error week ${result.message}")
                    _stateListMatchesCompetition.value = MatchesCompetitionState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    Log.e("week", "loading")
                    _stateListMatchesCompetition.value = MatchesCompetitionState(isLoading = true)
                }
                is Resource.Success -> {
                    Log.e("week", "success ${result.data}")
                    _stateListMatchesCompetition.value = MatchesCompetitionState(info = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun getAllMatchesTeamToday(currentDay: String){
        getAllTodayMatchesTeamUseCase(currentDay).onEach { result->
            when(result){
                is Resource.Error -> {
                    Log.e("favorite match", "match ${result.message}")
                    _stateListMatchesTeam.value = MatchesTeamState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    Log.e("favorite match", "loading")
                    _stateListMatchesTeam.value = MatchesTeamState(isLoading = true)
                }
                is Resource.Success -> {
                    Log.e("favorite match", "success ${result.data}")
                    _stateListMatchesTeam.value = MatchesTeamState(info = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}