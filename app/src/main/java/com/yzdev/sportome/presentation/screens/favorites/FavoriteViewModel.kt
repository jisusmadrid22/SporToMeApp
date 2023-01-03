package com.yzdev.sportome.presentation.screens.favorites

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yzdev.sportome.common.Resource
import com.yzdev.sportome.domain.model.LocalCompetition
import com.yzdev.sportome.domain.model.LocalTeam
import com.yzdev.sportome.domain.use_case.favoriteCompetition.CompetitionUseCaseFormat
import com.yzdev.sportome.domain.use_case.favoriteTeam.TeamUseCaseFormat
import com.yzdev.sportome.presentation.screens.tutorial.CompetitionState
import com.yzdev.sportome.presentation.screens.tutorial.TeamState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val teamUseCase: TeamUseCaseFormat,
    private val competitionUseCase: CompetitionUseCaseFormat
) : ViewModel() {

    init {
        viewModelScope.launch {
            getAllFavoriteCompetition()
            getAllFavoriteTeam()
        }
    }

    private val _stateListCompetition = mutableStateOf(CompetitionState())
    val stateListCompetition: State<CompetitionState> = _stateListCompetition

    private val _stateListTeam = mutableStateOf(TeamState())
    val stateListTeam: State<TeamState> = _stateListTeam

    /** get all competition from db*/
    private suspend fun getAllFavoriteCompetition(){
        competitionUseCase.getAllLocalFavoriteCompetitionUseCase().onEach { result->
            _stateListCompetition.value = CompetitionState(info = result)
        }.launchIn(viewModelScope)
    }

    /** get all team from db*/
    private suspend fun getAllFavoriteTeam(){
        teamUseCase.getAllLocalFavoriteTeamUseCase().onEach { result->
            _stateListTeam.value = TeamState(info = result)
        }.launchIn(viewModelScope)
    }

    /** QUERYS ****************************************************************************/

    private val queryLeague = mutableStateOf("")

    private val queryTeam = mutableStateOf("")

    /** query function*/
    fun getQueryByNumberStep(state: Boolean): String{
        return when(state){
            false-> queryLeague.value
            true-> queryTeam.value
            else-> ""
        }
    }

    fun changeQueryByNumber(state: Boolean, query: String){
        when(state){
            false-> queryLeague.value = query
            true-> queryTeam.value = query
        }
    }

    /** clear querys*/
    fun clearQuery(
        isSelected: Boolean
    ){
        if (isSelected){
            queryLeague.value = ""
        }else{
            queryTeam.value = ""
        }
    }

    /** competition*/
    private val _filteredCompetition = mutableStateListOf<LocalCompetition>()
    val filteredCompetition: List<LocalCompetition> = _filteredCompetition

    fun queryCompetition(listParent: List<LocalCompetition>){
        _filteredCompetition.clear()
        Log.e("query", "value competition ${queryLeague.value}")
        if (queryLeague.value.isEmpty()){
            _filteredCompetition.addAll(listParent)
        }else{
            val list = listParent.filter { it.name.lowercase().startsWith(queryLeague.value.lowercase()) }
            _filteredCompetition.addAll(list)
        }
    }

    /** team*/
    private val _filteredTeam = mutableStateListOf<LocalTeam>()
    val filteredTeam: List<LocalTeam> = _filteredTeam

    fun queryTeam(listParent: List<LocalTeam>){
        _filteredTeam.clear()
        Log.e("query", "value competition ${queryTeam.value}")
        if (queryTeam.value.isEmpty()){
            _filteredTeam.addAll(listParent)
        }else{
            val list = listParent.filter { it.name.lowercase().startsWith(queryTeam.value.lowercase()) }
            _filteredTeam.addAll(list)
        }
    }

    /***************************************************************/
}