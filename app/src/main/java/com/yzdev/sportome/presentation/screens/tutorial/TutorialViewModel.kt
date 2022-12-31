package com.yzdev.sportome.presentation.screens.tutorial

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yzdev.sportome.common.*
import com.yzdev.sportome.domain.use_case.getAllCountries.GetAllCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TutorialViewModel @Inject constructor(
    private val getAllCountriesUseCase: GetAllCountriesUseCase
) : ViewModel() {

    private val _stateListCountry = mutableStateOf(CountryState())
    val stateListCountry: State<CountryState> = _stateListCountry

    init {
        viewModelScope.launch(Dispatchers.IO) {
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

    private val _countrySelected: MutableState<Country?> = mutableStateOf(null)
    val countrySelected: State<Country?> = _countrySelected

    private val _leagueSelected: MutableState<League?> = mutableStateOf(null)
    val leagueSelected: State<League?> = _leagueSelected

    private val _teamSelected: MutableState<Team?> = mutableStateOf(null)
    val teamSelected: State<Team?> = _teamSelected

    /************************************************/

    /** sport filter*/
    private val _filteredListSport = mutableStateListOf<Sport>()
    val filteredListSport: List<Sport> = _filteredListSport

    fun filterListSport(
        listParent: List<Sport>,
        query: String
    ){
        _filteredListSport.clear()
        if(query.isEmpty()){
            _filteredListSport.addAll(listParent)
        }else{
            val filtered = listParent.filter {
                it.name.toLowerCase(Locale.getDefault()).startsWith(query.toLowerCase(Locale.getDefault()))
            }

            _filteredListSport.addAll(filtered)
        }
    }
    /****************************************************/

    /** country filter*/
    private val _filteredListCountry = mutableStateListOf<Country>()
    val filteredListCountry: List<Country> = _filteredListCountry

    fun filterListCountry(
        listParent: List<Country>,
        query: String
    ){
        _filteredListCountry.clear()
        if(query.isEmpty()){
            _filteredListCountry.addAll(listParent)
        }else{
            val filtered = listParent.filter {
                it.name.toLowerCase(Locale.getDefault()).startsWith(query.toLowerCase(Locale.getDefault()))
            }

            _filteredListCountry.addAll(filtered)
        }
    }
    /*****************************************************/

    /** league filter*/
    private val _filteredListLeague = mutableStateListOf<League>()
    val filteredListLeague: List<League> = _filteredListLeague

    fun filterListLeague(
        listParent: List<League>,
        query: String
    ){
        _filteredListLeague.clear()
        if(query.isEmpty()){
            _filteredListLeague.addAll(listParent)
        }else{
            val filtered = listParent.filter {
                it.name.toLowerCase(Locale.getDefault()).startsWith(query.toLowerCase(Locale.getDefault()))
            }

            _filteredListLeague.addAll(filtered)
        }
    }
    /*****************************************************/

    /** league filter*/
    private val _filteredListTeam = mutableStateListOf<Team>()
    val filteredListTeam: List<Team> = _filteredListTeam

    fun filterListTeam(
        listParent: List<Team>,
        query: String
    ){
        _filteredListTeam.clear()
        if(query.isEmpty()){
            _filteredListTeam.addAll(listParent)
        }else{
            val filtered = listParent.filter {
                it.name.toLowerCase(Locale.getDefault()).startsWith(query.toLowerCase(Locale.getDefault()))
            }

            _filteredListTeam.addAll(filtered)
        }
    }
    /*****************************************************/

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
        if(item != null){
            filterListCountry(listParent = getCountryBySport(item.id), query = "")
        }else{
            _filteredListCountry.clear()
        }
    }

    fun changeCountry(item: Country?){
        _countrySelected.value = item
        if(item != null){
            filterListLeague(listParent = getLeaguesByCountry(item.id), query = "")
        }else{
            _filteredListLeague.clear()
        }
    }

    fun changeLeague(item: League?){
        _leagueSelected.value = item
        if(item != null){
            filterListTeam(listParent = item.teams, query = "")
        }else{
            _filteredListTeam.clear()
        }
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