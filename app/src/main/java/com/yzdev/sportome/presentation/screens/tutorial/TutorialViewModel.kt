package com.yzdev.sportome.presentation.screens.tutorial

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yzdev.sportome.common.*
import com.yzdev.sportome.domain.model.LocalCompetition
import com.yzdev.sportome.domain.model.LocalCountry
import com.yzdev.sportome.domain.use_case.favoriteCompetition.CompetitionUseCaseFormat
import com.yzdev.sportome.domain.use_case.getAllCompetitionQueryRemote.GetAllCompetitionQueryRemoteUseCase
import com.yzdev.sportome.domain.use_case.getAllCountries.GetAllCountriesUseCase
import com.yzdev.sportome.domain.use_case.getAllSeasonsYear.GetAllSeasonsYearUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TutorialViewModel @Inject constructor(
    private val getAllCountriesUseCase: GetAllCountriesUseCase,
    private val getAllCompetitionQueryRemoteUseCase: GetAllCompetitionQueryRemoteUseCase,
    private val competitionUseCase: CompetitionUseCaseFormat,
    private val getAllSeasonsYearUseCase: GetAllSeasonsYearUseCase
) : ViewModel() {

    private val _stateListCountry = mutableStateOf(CountryState())
    val stateListCountry: State<CountryState> = _stateListCountry

    private val _stateListCompetition = mutableStateOf(CompetitionState())
    val stateListCompetition: State<CompetitionState> = _stateListCompetition

    init {
        viewModelScope.launch {
            getAllSeasonsYear()
        }
    }

    /** use case functions*/
    /** get all countries*/
    suspend fun getAllCountries(){
        Log.e("countries", "init")
        getAllCountriesUseCase().onEach { result->
            when(result){
                is Resource.Error -> {
                    _stateListCountry.value = CountryState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _stateListCountry.value = CountryState(isLoading = true)
                }
                is Resource.Success -> {
                    _stateListCountry.value = CountryState(info = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    /** get all competition from api*/
    suspend fun getAllCompetitionsRemote(){
        Log.e("competition", "init competition ${countrySelected.value}")
        countrySelected.value?.code?.let {
            getAllCompetitionQueryRemoteUseCase(countryCode = it).onEach { result->
                when(result){
                    is Resource.Error -> {
                        _stateListCompetition.value = CompetitionState(error = result.message ?: "An unexpected error occurred")
                    }
                    is Resource.Loading -> {
                        _stateListCompetition.value = CompetitionState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _stateListCompetition.value = CompetitionState(info = result.data)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    /** insert favorite competition*/
    suspend fun insertFavoriteCompetition(localCompetition: LocalCompetition){
        try {
            competitionUseCase.insertFavoriteCompetition(
                localCompetition = localCompetition
            )
        } catch(e: InvalidException) {
            Log.e("Invalid Exception", "Error -> $e")
        }
    }

    /** get all seasons year from api or db*/
    private suspend fun getAllSeasonsYear(){
        Log.e("countries", "init")
        delay(15000)
        getAllSeasonsYearUseCase().onEach { result->
            when(result){
                is Resource.Error -> {
                    Log.e("seasons", "error -> ${result.message}")
                }
                is Resource.Loading -> {
                    Log.e("countries", "loading")
                }
                is Resource.Success -> {
                    Log.e("countries", "success")
                }
            }
        }.launchIn(viewModelScope)
    }

    /** query state*/
    val querySport = mutableStateOf("")

    val queryCountry = mutableStateOf("")

    val queryLeague = mutableStateOf("")

    val queryTeam = mutableStateOf("")

    /*********************/

    /** item selected*/
    private val _sportSelected: MutableState<Sport?> = mutableStateOf(null)
    val sportSelected: State<Sport?> = _sportSelected

    private val _countrySelected: MutableState<LocalCountry?> = mutableStateOf(null)
    val countrySelected: State<LocalCountry?> = _countrySelected

    private val _leagueSelected: MutableState<LocalCompetition?> = mutableStateOf(null)
    val leagueSelected: State<LocalCompetition?> = _leagueSelected

    private val _teamSelected: MutableState<Team?> = mutableStateOf(null)
    val teamSelected: State<Team?> = _teamSelected

    /************************************************/

    /** clear querys*/
    fun clearQuery(
        numberStep: Int
    ){
        when(numberStep){
            1->{
                queryCountry.value = ""
                queryLeague.value = ""
                queryTeam.value = ""
            }
            2->{
                queryLeague.value = ""
                queryTeam.value = ""
            }
            3->{
                queryTeam.value = ""
            }
            else->{

            }
        }
    }

    /******************************************************/

    /** change selected items*/
    fun changeSport(item: Sport?){
        _sportSelected.value = item
    }

    fun changeCountry(item: LocalCountry?){
        _countrySelected.value = item
    }

    fun changeLeague(item: LocalCompetition?){
        _leagueSelected.value = item
    }

    fun changeTeam(item: Team?){
        _teamSelected.value = item
    }

    /*******************************************************/

    /** query function*/
    fun getQueryByNumberStep(numberStep: Int): String{
        return when(numberStep){
            1-> querySport.value
            2-> queryCountry.value
            3-> queryLeague.value
            4-> queryTeam.value
            else-> ""
        }
    }

    fun changeQueryByNumber(number: Number, query: String){
        when(number){
            1-> querySport.value = query
            2-> queryCountry.value = query
            3-> queryLeague.value = query
            4-> queryTeam.value = query
        }
    }

    /***********************************/
}