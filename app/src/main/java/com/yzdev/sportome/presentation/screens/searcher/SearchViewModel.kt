package com.yzdev.sportome.presentation.screens.searcher

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yzdev.sportome.common.Resource
import com.yzdev.sportome.domain.model.LocalCompetition
import com.yzdev.sportome.domain.model.LocalCountry
import com.yzdev.sportome.domain.use_case.getAllCompetitionQueryRemote.GetAllCompetitionQueryRemoteUseCase
import com.yzdev.sportome.domain.use_case.getAllCountries.GetAllCountriesUseCase
import com.yzdev.sportome.presentation.screens.tutorial.CompetitionState
import com.yzdev.sportome.presentation.screens.tutorial.CountryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getAllCountriesUseCase: GetAllCountriesUseCase,
    private val getAllCompetitionQueryRemoteUseCase: GetAllCompetitionQueryRemoteUseCase,
): ViewModel() {

    private val _stateListCountry = mutableStateOf(CountryState())
    val stateListCountry: State<CountryState> = _stateListCountry

    private val _stateListCompetition = mutableStateOf(CompetitionState())
    val stateListCompetition: State<CompetitionState> = _stateListCompetition

    private val _countrySelected: MutableState<LocalCountry?> = mutableStateOf(null)
    val countrySelected: State<LocalCountry?> = _countrySelected

    private val _leagueSelected: MutableState<LocalCompetition?> = mutableStateOf(null)
    val leagueSelected: State<LocalCompetition?> = _leagueSelected

    init {
        viewModelScope.launch {
            getAllCountries()
        }
    }

    /** use case functions*/
    /** get all countries*/
    private suspend fun getAllCountries(){
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

    fun changeCountry(item: LocalCountry?){
        _countrySelected.value = item
    }

    fun changeLeague(item: LocalCompetition?){
        _leagueSelected.value = item
    }

    private val queryCountry = mutableStateOf("")

    private val queryLeague = mutableStateOf("")

    /** clear querys*/
    fun clearQuery(
        numberStep: Int
    ){
        when(numberStep){
            1->{
                queryCountry.value = ""
                queryLeague.value = ""
            }
            2->{
                queryLeague.value = ""
            }
            else->{

            }
        }
    }

    fun getQueryByNumberStep(numberStep: Int): String{
        return when(numberStep){
            1-> queryCountry.value
            2-> queryLeague.value
            else-> ""
        }
    }

    fun changeQueryByNumber(number: Number, query: String){
        when(number){
            1-> queryCountry.value = query
            2-> queryLeague.value = query
        }
    }

    //queries
    /** countries*/
    private val _filteredCountries = mutableStateListOf<LocalCountry>()
    val filteredCountries: List<LocalCountry> = _filteredCountries

    fun queryCountries(listParent: List<LocalCountry>){
        _filteredCountries.clear()
        Log.e("query", "value country ${queryCountry.value}")
        if (queryCountry.value.isEmpty()){
            _filteredCountries.addAll(listParent)
        }else{
            val list = listParent.filter { it.name.lowercase().startsWith(queryCountry.value.lowercase()) }
            _filteredCountries.addAll(list)
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
}