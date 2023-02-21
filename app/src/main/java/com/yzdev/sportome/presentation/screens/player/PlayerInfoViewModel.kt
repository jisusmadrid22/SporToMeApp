package com.yzdev.sportome.presentation.screens.player

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.Resource
import com.yzdev.sportome.domain.use_case.getAllSeasonPlayer.GetAllSeasonPlayerUseCase
import com.yzdev.sportome.domain.use_case.getInfoPlayer.GetInfoPlayerUseCase
import com.yzdev.sportome.domain.use_case.getTransferPlayer.GetTransferPlayerUseCase
import com.yzdev.sportome.domain.use_case.getTrophiesPlayer.GetTrophiesPlayerUseCase
import com.yzdev.sportome.domain.use_case.seasonPlayer.SeasonPlayerUseCase
import com.yzdev.sportome.domain.use_case.seasonPlayer.getAllSeasonPlayer.GetAllSeasonPlayerDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerInfoViewModel @Inject constructor(
    private val getInfoPlayerUseCase: GetInfoPlayerUseCase,
    private val getAllSeasonPlayerUseCase: GetAllSeasonPlayerUseCase,
    private val seasonPlayerUseCase: SeasonPlayerUseCase,
    private val getTransferPlayerUseCase: GetTransferPlayerUseCase,
    private val getTrophiesPlayerUseCase: GetTrophiesPlayerUseCase
): ViewModel() {

    private val _stateResumePlayer = mutableStateOf(PlayerResumeState())
    val stateResumePlayer: State<PlayerResumeState> = _stateResumePlayer

    private val _stateSeasonPlayer = mutableStateOf(SeasonPlayerState())
    val stateSeasonPlayer: State<SeasonPlayerState> = _stateSeasonPlayer

    private val _stateCareerPlayer = mutableStateOf(CareerPlayerState())
    val stateCareerPlayer: State<CareerPlayerState> = _stateCareerPlayer

    private val _stateTrophiesPlayer = mutableStateOf(TrophiesPlayerState())
    val stateTrophiesPlayer: State<TrophiesPlayerState> = _stateTrophiesPlayer

    private suspend fun getSeasonPlayer(agePlayer: Int){
        getAllSeasonPlayerUseCase(agePlayer = agePlayer).onEach { result->
            when(result){
                is Resource.Error -> {
                    _stateSeasonPlayer.value = SeasonPlayerState(error = result.message ?: AppResource.getString(R.string.erroGeneric))
                }
                is Resource.Loading -> {
                    _stateSeasonPlayer.value = SeasonPlayerState(isLoading = true)
                }
                is Resource.Success -> {
                    _stateSeasonPlayer.value = SeasonPlayerState(info = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    private suspend fun getAllSeasonsPlayer(playerId: Int){
        Log.e("countries", "init")
        getAllSeasonPlayerUseCase(null).onEach { result->
            when(result){
                is Resource.Error -> {
                    Log.e("seasons", "error -> ${result.message}")
                }
                is Resource.Loading -> {
                    Log.e("countries", "loading")
                }
                is Resource.Success -> {
                    Log.e("countries", "success")

                    getCareerPlayer(playerId = playerId)    //only first request

                    getTrophiesPlayer(playerId = playerId)  //only first request

                    getPlayerResume(playerId = playerId, season = null)
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun initRequest(playerId: Int){
        delay(20000)

        getAllSeasonsPlayer(playerId)
    }

    private suspend fun getTrophiesPlayer(playerId: Int){
        getTrophiesPlayerUseCase(playerId = playerId).onEach {result->
            when(result){
                is Resource.Error -> {
                    _stateTrophiesPlayer.value = TrophiesPlayerState(error = result.message ?: AppResource.getString(R.string.erroGeneric))
                }
                is Resource.Loading -> {
                    _stateTrophiesPlayer.value = TrophiesPlayerState(isLoading = true)
                }
                is Resource.Success -> {
                    _stateTrophiesPlayer.value = TrophiesPlayerState(info = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    private suspend fun getCareerPlayer(playerId: Int){
        getTransferPlayerUseCase(playerId = playerId).onEach {result->
            when(result){
                is Resource.Error -> {
                    _stateCareerPlayer.value = CareerPlayerState(error = result.message ?: AppResource.getString(R.string.erroGeneric))
                }
                is Resource.Loading -> {
                    _stateCareerPlayer.value = CareerPlayerState(isLoading = true)
                }
                is Resource.Success -> {
                    _stateCareerPlayer.value = CareerPlayerState(info = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun getPlayerResume(playerId: Int, season: Int? = null){
        if (season != null){
            /** request for any season selected*/
            getInfoPlayerUseCase(playerId = playerId, season = season).onEach { result->
                when(result){
                    is Resource.Error -> {
                        _stateResumePlayer.value = PlayerResumeState(error = result.message ?: AppResource.getString(
                            R.string.erroGeneric))
                    }
                    is Resource.Loading -> {
                        _stateResumePlayer.value = PlayerResumeState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _stateResumePlayer.value = PlayerResumeState(info = result.data)
                    }
                }
            }.launchIn(viewModelScope)
        }else{
            /** firsts request*/
            Log.e("PlayerResume", "null season")
            val data = seasonPlayerUseCase.getAllSeasonPlayerWithOutFlow()

            if (data.isNotEmpty()){
                Log.e("PlayerResume", "flow db get season $data")
                getInfoPlayerUseCase(playerId = playerId, season = data.last().year.toInt()).onEach { result->
                    when(result){
                        is Resource.Error -> {
                            _stateResumePlayer.value = PlayerResumeState(error = result.message ?: AppResource.getString(
                                R.string.erroGeneric))
                        }
                        is Resource.Loading -> {
                            _stateResumePlayer.value = PlayerResumeState(isLoading = true)
                        }
                        is Resource.Success -> {
                            _stateResumePlayer.value = PlayerResumeState(info = result.data)

                            stateResumePlayer.value.info?.let {
                                Log.e("PlayerResume", "info not null")
                                it.response.first().player?.let {player->
                                    Log.e("PlayerResume", "info first player not null")
                                    getSeasonPlayer(player.age)
                                }
                            }
                        }
                    }
                }.launchIn(viewModelScope)
            }
        }
    }
}