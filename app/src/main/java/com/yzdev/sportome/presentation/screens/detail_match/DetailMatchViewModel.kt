package com.yzdev.sportome.presentation.screens.detail_match

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.Resource
import com.yzdev.sportome.domain.use_case.getH2hMatch.GetH2hMatchUseCase
import com.yzdev.sportome.domain.use_case.getMatchDetailRemote.GetMatchDetailRemoteUseCase
import com.yzdev.sportome.domain.use_case.getPredictionMatch.GetPredictionMatchUseCase
import com.yzdev.sportome.presentation.screens.tutorial.CompetitionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMatchViewModel @Inject constructor(
    private val getMatchDetailRemoteUseCase: GetMatchDetailRemoteUseCase,
    private val getH2hMatchUseCase: GetH2hMatchUseCase,
    private val getPredictionMatchUseCase: GetPredictionMatchUseCase
): ViewModel() {

    private val _stateDetail = mutableStateOf(DetailMatchState())
    val stateListDetail: State<DetailMatchState> = _stateDetail

    private val _stateH2h = mutableStateOf(H2hMatchState())
    val stateH2h: State<H2hMatchState> = _stateH2h

    private val _statePrediction = mutableStateOf(PredictionMatchState())
    val statePrediction: State<PredictionMatchState> = _statePrediction

    /** get detail match by id*/
    suspend fun getDetailMatchByID(id: Long){
        getMatchDetailRemoteUseCase(id).onEach { result->
            when(result){
                is Resource.Error -> {
                    _stateDetail.value = DetailMatchState(error = result.message ?: AppResource.getString(R.string.erroGeneric))
                }
                is Resource.Loading -> {
                    _stateDetail.value = DetailMatchState(isLoading = true)
                }
                is Resource.Success -> {
                    _stateDetail.value = DetailMatchState(info = result.data)

                    result.data?.let {
                        getH2hMatch("${it.teams.home.id}-${it.teams.away.id}")
                    }

                    result.data?.let {
                        getPrediction(it.fixture.id)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private suspend fun getH2hMatch(ids: String){
        getH2hMatchUseCase(ids).onEach { result->
            when(result){
                is Resource.Error -> {
                    _stateH2h.value = H2hMatchState(error = result.message ?: AppResource.getString(R.string.erroGeneric))
                }
                is Resource.Loading -> {
                    _stateH2h.value = H2hMatchState(isLoading = true)
                }
                is Resource.Success -> {
                    _stateH2h.value = H2hMatchState(info = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    private suspend fun getPrediction(id: Int){
        getPredictionMatchUseCase(id).onEach { result->
            when(result){
                is Resource.Error -> {
                    _statePrediction.value = PredictionMatchState(error = result.message ?: AppResource.getString(R.string.erroGeneric))
                }
                is Resource.Loading -> {
                    _statePrediction.value = PredictionMatchState(isLoading = true)
                }
                is Resource.Success -> {
                    _statePrediction.value = PredictionMatchState(info = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun setErrorId(){
        _stateDetail.value = DetailMatchState(error = AppResource.getString(R.string.erroGeneric))
    }

}